package com.my.sparta.lecture.controller.`interface`.response

import com.my.sparta.lecture.application.domain.entity.Lecture

data class GetSelectedLectureInfoResponse constructor(
    val lectureId: String,
    val lectureName: String,
    val speaker: String,
) {

    companion object {
        fun toLectureResponse(lecture: Lecture): GetSelectedLectureInfoResponse {
            return GetSelectedLectureInfoResponse(lecture.id, lecture.lectureName, lecture.speaker)
        }
    }
}
