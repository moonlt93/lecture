package com.my.sparta.lecture.controller.query

import com.my.sparta.lecture.controller.`interface`.response.GetNonSelectedLectureInfoListResponse
import com.my.sparta.lecture.controller.`interface`.response.GetSelectedLectureInfoList
import com.my.sparta.lecture.service.GetCompletedLectureService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lecture")
class LectureQueryController(
    private val getCompletedLectureService: GetCompletedLectureService
) {

    @GetMapping("/active")
    fun getCompleteYetScheduleList(
    ): GetNonSelectedLectureInfoListResponse {
        val lectures = getCompletedLectureService.getAvailableLectures();
        return GetNonSelectedLectureInfoListResponse(GetNonSelectedLectureInfoListResponse.mapToResponseList(lectures))
    }

    // 내가 신청 완료한 강의 목록
    @GetMapping("/cutoff/{userId}")
    fun getCompletedScheduleList(
        @PathVariable("userId") userId: String
    ): GetSelectedLectureInfoList {
        val lectures = getCompletedLectureService.getSelectedLectures(userId);
        return GetSelectedLectureInfoList(GetSelectedLectureInfoList.mapToResponseList(lectures))
    }
}