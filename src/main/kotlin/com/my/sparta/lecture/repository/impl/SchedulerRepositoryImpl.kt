package com.my.sparta.lecture.repository.impl

import com.my.sparta.lecture.domain.entity.Scheduler
import com.my.sparta.lecture.repository.SchedulerRepository
import com.my.sparta.lecture.repository.orm.jpa.SchedulerJpaRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Repository
class SchedulerRepositoryImpl(
    private val schedulerJpaRepository: SchedulerJpaRepository
) : SchedulerRepository {


    override fun saveScheduler(scheduler: Scheduler) {

        schedulerJpaRepository.save(scheduler)

    }

    override fun getSchedulerByLectureId(lectureId: String, targetDate: LocalDateTime): Scheduler {

        val date = LocalDate.from(targetDate)
        val time = LocalTime.from(targetDate)

        return schedulerJpaRepository.findByTargetLecture(lectureId, date, time)
            .orElseThrow { EntityNotFoundException("Scheduler not found for lectureId: $lectureId") }

    }


    override fun getSchedulerByStatus(): List<Scheduler> {

        return schedulerJpaRepository.findByStatusProgress(Scheduler.DeadLine.PROGRESS);

    }

    override fun getFinishedSchedules(userScheduleIds: List<String>): List<Scheduler> {

        return schedulerJpaRepository.findAllById(userScheduleIds);
    }
}