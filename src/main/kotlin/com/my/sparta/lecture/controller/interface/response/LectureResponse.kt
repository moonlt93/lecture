package com.my.sparta.lecture.controller.`interface`.response

import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Data
@NoArgsConstructor
data class LectureResponse(
    val lectureId: String,
    val userId: String,
    val createDate: LocalDateTime,

    ) {

    companion object {
        fun fromDomain(lectureId: String, userId: String, createDate: LocalDateTime): LectureResponse {
            return LectureResponse(lectureId, userId, createDate)
        }
    }

}
