package com.my.sparta.lecture.repository

import com.my.sparta.lecture.application.domain.entity.Scheduler
import java.time.LocalDateTime

interface SchedulerRepository {

    fun getSchedulerByLectureId(lectureId: String, targetDate: LocalDateTime): Scheduler
    fun getSchedulerByStatus(): List<Scheduler>
    fun getFinishedSchedules(userScheduleIds: List<String>): List<Scheduler>
    fun getSchedulerById(schedulerId: String): Scheduler

    fun saveScheduler(scheduler: Scheduler)


}
