package com.my.sparta.lecture.controller

import com.my.sparta.lecture.controller.`interface`.request.ApplyLectureRequest
import com.my.sparta.lecture.controller.`interface`.response.LectureResponse
import com.my.sparta.lecture.application.service.ApplyLectureService
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
class LectureCommandController(
    private val applyLectureService: ApplyLectureService
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    /*
    특강 신청 api 이미 등록된 특강 목록이 있고 얘는 id 입력시 신청하는거지
    * */

    @PostMapping
    fun applyForSpecialLecture(
        @RequestBody applyLectureRequest: ApplyLectureRequest
    ): LectureResponse {

        logger.info("applyForSpecialLecture flow doing userId : {}", applyLectureRequest.userId)
        val domain = applyLectureService.applyUserByRequest(applyLectureRequest);
        return LectureResponse.fromDomain(domain.lectureId, domain.users.id, domain.initDate.createAt)
    }
}