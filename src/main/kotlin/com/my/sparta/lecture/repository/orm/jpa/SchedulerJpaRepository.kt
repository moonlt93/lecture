package com.my.sparta.lecture.repository.orm.jpa

import com.my.sparta.lecture.domain.entity.Scheduler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

interface SchedulerJpaRepository : JpaRepository<Scheduler, Long> {

    @Query("select sc from Scheduler as sc where sc.lectureId = :lectureId and sc.duration.targetDate = :date  " +
            "AND :targetTime BETWEEN sc.duration.startTime AND sc.duration.endTime ")
    fun findByTargetLecture(lectureId: String, date: LocalDate, targetTime: LocalTime): Optional<Scheduler>

}
