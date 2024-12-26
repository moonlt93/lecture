package com.my.sparta.lecture.controller.`interface`.response

import com.my.sparta.lecture.domain.entity.Scheduler
import com.my.sparta.lecture.domain.entity.UserScheduler
import lombok.Data
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Data
class GetSchedulerInfoResponse(
    var id: String,

    var lectureId: String,

    var createAt: LocalDateTime,
    var updateAt: LocalDateTime,

    var day: String,
    var capacity: Int,
    val targetDate: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val deadLine: String
) {
    companion object {
        fun toScheduler(scheduler: Scheduler): GetSchedulerInfoResponse {
            return GetSchedulerInfoResponse(
                scheduler.id,
                scheduler.lectureId,
                scheduler.createAt,
                scheduler.updateAt,
                scheduler.day.name,
                scheduler.capacity,
                scheduler.duration.targetDate,
                scheduler.duration.startTime,
                scheduler.duration.endTime,
                scheduler.deadLine.name
            )
        }
    }

    constructor(id: String, lectureId: String) : this(
        id = id,
        lectureId = lectureId,
        createAt = LocalDateTime.now(),
        updateAt = LocalDateTime.now(),
        day = "",                  // 기본값 설정
        capacity = 0,             // 기본값 설정
        targetDate = LocalDate.now(),
        startTime = LocalTime.of(9, 0),   // 기본 예시
        endTime = LocalTime.of(17, 0),    // 기본 예시
        deadLine = ""              // 기본값 설정
    )

}
