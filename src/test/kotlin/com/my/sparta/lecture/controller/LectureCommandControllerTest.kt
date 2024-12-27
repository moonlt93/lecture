package com.my.sparta.lecture.controller


import com.fasterxml.jackson.databind.ObjectMapper
import com.my.sparta.lecture.controller.`interface`.request.ApplyLectureRequest
import com.my.sparta.lecture.controller.`interface`.response.LectureResponse
import com.my.sparta.lecture.controller.query.LectureQueryController
import com.my.sparta.lecture.application.domain.entity.Scheduler
import com.my.sparta.lecture.application.domain.entity.UserScheduler
import com.my.sparta.lecture.application.domain.entity.Users
import com.my.sparta.lecture.application.service.ApplyLectureService
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import kotlin.test.assertEquals
import kotlin.test.junit5.JUnit5Asserter.assertEquals


class LectureCommandControllerTest {


    private val applyLectureService: ApplyLectureService = mockk()

    private lateinit var lectureCommandController: LectureCommandController

    @BeforeEach
    fun setUp() {
        clearAllMocks()
        lectureCommandController = LectureCommandController(applyLectureService)
    }

    @Test
    fun `applyForSpecialLecture - 성공 테스트`() {
        // given
        val requestBody = ApplyLectureRequest(
            userId = "user1",
            lectureId = "Spring Framework",
            applicationDate = "2024-12-31 10:00:00"
        )

        // 가짜 도메인 객체
        val userSchedulerMock = UserScheduler(
            "Spring Framework",
            Users("user1"),
            Scheduler("scheduledId", "Spring Framework")
        )

        // Mock 동작 정의
        every { applyLectureService.applyUserByRequest(requestBody) } returns userSchedulerMock

        //when
        val response: LectureResponse = lectureCommandController.applyForSpecialLecture(requestBody)

            //then
            // 예: 응답 값 검증
            assertEquals("Spring Framework",response.lectureId)
            assertEquals("user1",response.userId)


        // 서비스 호출 횟수 검증
        verify(exactly = 1) { applyLectureService.applyUserByRequest(requestBody) }
    }
}