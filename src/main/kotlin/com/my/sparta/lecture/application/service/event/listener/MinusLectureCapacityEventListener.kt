package com.my.sparta.lecture.application.service.event.listener

import com.my.sparta.lecture.application.domain.entity.Scheduler
import com.my.sparta.lecture.application.domain.event.CapacityExceededEvent
import com.my.sparta.lecture.application.domain.event.MinusCapacityEvent
import com.my.sparta.lecture.application.common.exception.CapacityExceededException
import com.my.sparta.lecture.repository.SchedulerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class MinusLectureCapacityEventListener(
    private val schedulerRepository: SchedulerRepository,
    private val eventPublisher: ApplicationEventPublisher
) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)


    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    fun handleMinusLectureCapacityEvent(event: MinusCapacityEvent) {

        logger.info("Handling MinusLectureCapacityEvent: ${event.schedulerId}")

        val scheduler = schedulerRepository.getSchedulerById(event.schedulerId)

        useCapacity(scheduler)

    }

    private fun useCapacity(scheduler: Scheduler) {

        try {

            scheduler.minusCapacity();

            check(scheduler.capacity != 0){
                scheduler.changeCutoffStatus();
            }

            schedulerRepository.saveScheduler(scheduler)

        } catch (ex: CapacityExceededException) {
            val currentCapacity = ex.currentCapacity;
            val schedulerId = ex.schedulerId

            val capacityExceededEvent = CapacityExceededEvent(schedulerId, currentCapacity);
            eventPublisher.publishEvent(capacityExceededEvent)
        } catch (ex: IllegalStateException) {
            logger.warn(ex.message, ex);
        }

    }


}