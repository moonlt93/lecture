package com.my.sparta.lecture.service

import com.my.sparta.lecture.controller.`interface`.request.ApplyLectureRequest
import com.my.sparta.lecture.domain.entity.Scheduler
import com.my.sparta.lecture.domain.entity.UserScheduler
import com.my.sparta.lecture.domain.entity.Users
import com.my.sparta.lecture.domain.event.CapacityExceededEvent
import com.my.sparta.lecture.domain.event.MinusCapacityEvent
import com.my.sparta.lecture.exception.CapacityExceededException
import com.my.sparta.lecture.repository.SchedulerRepository
import com.my.sparta.lecture.repository.UserSchedulerRepository
import jakarta.persistence.EntityExistsException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ApplyLectureService(
    private val schedulerRepository: SchedulerRepository,
    private val userSchedulesRepository: UserSchedulerRepository,
    private val eventPublisher: ApplicationEventPublisher

) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    // 수강 신청을 하려는데 , 수강 목록에서 선택을 해서 내가 신청을 할텐데 requestBody로 해당 class id로 조회해와서 언제 며칠에 신청하는지 받아야할듯
    fun applyUserByRequest(request: ApplyLectureRequest): UserScheduler {

        var applicationDate = mapToDateTime(request)
        val scheduler = schedulerValidator(request, applicationDate)

        // 신청시 수강가능 인원 감소 이벤트 발생
        val minusCapacityEvent = MinusCapacityEvent(scheduler.id);
        eventPublisher.publishEvent(minusCapacityEvent)

        val createUserScheduler = UserScheduler.create(scheduler.lectureId, Users(request.userId), scheduler);
        val saveUserScheduler = userSchedulesRepository.saveUserScheduler(createUserScheduler)

        logger.info("해당 수강생은 신청을 완료하였습니다. : ${saveUserScheduler.toString()}")

        return saveUserScheduler;
    }

    private fun mapToDateTime(request: ApplyLectureRequest): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm:ss")

        val applicationDate = LocalDateTime.parse(request.applicationDate, formatter)
            ?: throw IllegalArgumentException("applicationDate cannot be null")
        return applicationDate
    }

    private fun schedulerValidator(
        request: ApplyLectureRequest,
        applicationDate: LocalDateTime
    ): Scheduler {
        try {
            // 신청 강의 스케쥴이 잡혀있는지 확인
            val scheduler = schedulerRepository.getSchedulerByLectureId(request.lectureId, applicationDate)

            // 해당 강의에 이미 신청했는지 확인
            val userSchedulerWithSchedulerId =
                userSchedulesRepository.getUserSchedulerWithSchedulerId(scheduler.id, request.userId)

            check(scheduler.capacity != 0) {
                throw CapacityExceededException(scheduler.id, scheduler.capacity)
            }

            check(userSchedulerWithSchedulerId == null) {
                throw EntityExistsException("해당 강의는 이미 신청한 적이 있는 강의 입니다.")
            }

            return scheduler

        } catch (ex: CapacityExceededException) {

            println("Logging CapacityExceededException: ${ex.message}")

            val currentCapacity = ex.currentCapacity;
            val schedulerId = ex.schedulerId

            val capacityExceededEvent = CapacityExceededEvent(schedulerId, currentCapacity);
            eventPublisher.publishEvent(capacityExceededEvent)

            throw ex

        }
    }

}