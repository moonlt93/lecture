package com.my.sparta.lecture.service.event.listener

import com.my.sparta.lecture.domain.event.CapacityExceededEvent
import com.my.sparta.lecture.repository.SchedulerRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CapacityExceededExceptionListener(
    private val schedulerRepository: SchedulerRepository
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    fun handleCapacityExceededEvent(exception: CapacityExceededEvent) {

        val schedulerId = exception.schedulerId
        val currentCapacity = exception.currentCapacity

        check(currentCapacity == 0) {
            throw IllegalArgumentException("$currentCapacity 수량이 0보다 큰데 이벤트가 발행됨.");
        }

        val scheduler = schedulerRepository.getSchedulerById(schedulerId)
        scheduler.changeCutoffStatus()

        schedulerRepository.saveScheduler(scheduler)

    }
}