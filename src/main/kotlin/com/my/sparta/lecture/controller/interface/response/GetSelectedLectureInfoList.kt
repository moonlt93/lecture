package com.my.sparta.lecture.controller.`interface`.response

import com.my.sparta.lecture.domain.entity.Lecture

data class GetSelectedLectureInfoList constructor(

    val lectureInfoList: List<GetSelectedLectureInfoResponse>

) {

    companion object {

        fun mapToResponseList(lectures: List<Lecture>): List<GetSelectedLectureInfoResponse> {
            return lectures.stream()
                .map { lecture ->
                    GetSelectedLectureInfoResponse.toLectureResponse(lecture)
                }
                .toList()
        }

    }

}

