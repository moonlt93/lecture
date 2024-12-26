package com.my.sparta.lecture.controller.query

import com.my.sparta.lecture.controller.`interface`.response.GetNonSelectedLectureInfoListResponse
import com.my.sparta.lecture.controller.`interface`.response.GetSelectedLectureInfoList
import com.my.sparta.lecture.domain.entity.Lecture
import com.my.sparta.lecture.domain.entity.Scheduler
import com.my.sparta.lecture.service.GetLectureListService
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class LectureQueryControllerTest {

    // 순수 Mock 객체
    private val getLectureListService: GetLectureListService = mockk()

    // 직접 컨트롤러를 생성해서 주입
    private lateinit var lectureQueryController: LectureQueryController

    @BeforeEach
    fun setUp() {
        clearAllMocks()
        lectureQueryController = LectureQueryController(getLectureListService)
    }

    @Test
    fun `getCompleteYetScheduleList - 정상 동작 테스트`() {
        // given
        val mockLectures = listOf(
            Scheduler(
                "1", "lectureId1"
            ),
            Scheduler(
                "2", "lectureId2"
            )
        )
        every { getLectureListService.getAvailableLectures() } returns mockLectures

        // when
        val response: GetNonSelectedLectureInfoListResponse = lectureQueryController.getCompleteYetScheduleList()

        // then
        // 응답 구조 검증 (필드명은 실제 DTO 구조에 맞게 수정)
        // 여기서는 mapToResponseList(...) 결과가 GetNonSelectedLectureInfoListResponse 내부의 리스트에 매핑된다고 가정
        assertEquals(2, response.getSelectedLectureInfoResponse.size)
        assertEquals("lectureId1", response.getSelectedLectureInfoResponse[0].lectureId)
        assertEquals("lectureId2", response.getSelectedLectureInfoResponse[1].lectureId)
    }

    @Test
    fun `getCompletedScheduleList - 정상 동작 테스트`() {
        // given
        val userId = "user123"
        val mockLectures = listOf(
            Lecture(
                "1", "Spring Boot", "toby"
            ),
            Lecture(
                "2", "queryDsl", "김영한"
            )
        )
        every { getLectureListService.getSelectedLectures(userId) } returns mockLectures

        // when
        val response: GetSelectedLectureInfoList = lectureQueryController.getCompletedScheduleList(userId)

        // then
        // 내부 응답 구조 검증 (실제 DTO에 맞춰 수정)
        assertEquals(2, response.lectureInfoList.size)
        assertEquals("1", response.lectureInfoList[0].lectureId)
        assertEquals("Spring Boot", response.lectureInfoList[0].lectureName)
        assertEquals("2", response.lectureInfoList[1].lectureId)
        assertEquals("queryDsl", response.lectureInfoList[1].lectureName)

    }
}