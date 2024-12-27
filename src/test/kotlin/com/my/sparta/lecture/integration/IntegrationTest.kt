package com.my.sparta.lecture.integration

import com.my.sparta.lecture.controller.`interface`.request.ApplyLectureRequest
import com.my.sparta.lecture.repository.orm.jpa.LectureJpaRepository
import com.my.sparta.lecture.repository.orm.jpa.UserSchedulerJpaRepository
import com.my.sparta.lecture.repository.orm.jpa.UsersJpaRepository
import com.my.sparta.lecture.application.service.ApplyLectureService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IntegrationTest {

    @Autowired
    private lateinit var lectureJpaRepository: LectureJpaRepository

    @Autowired
    private lateinit var userSchedulerJpaRepository: UserSchedulerJpaRepository

    @Autowired
    private lateinit var usersJpaRepository: UsersJpaRepository

    @Autowired
    private lateinit var applyLectureService: ApplyLectureService

    private val applyRequests = mutableListOf<ApplyLectureRequest>()

    @BeforeEach
    fun setUp() {
        (1..40).forEach { i ->
            applyRequests.add(
                ApplyLectureRequest(
                    userId = "user$i",
                    lectureId = "Java Basics",
                    applicationDate = "2025-01-01 14:30:00"
                )
            )
        }

    }

    @AfterEach
    fun tearDown() {

        userSchedulerJpaRepository.deleteAllInBatch()
        applyRequests.clear()
    }

    @Test
    fun `H2에 사전 데이터가 잘 들어갔는지 확인`() {
        val allLectures = lectureJpaRepository.findAll()
        val allUsers = usersJpaRepository.findAll();
        println(allUsers)
        println(allLectures)
        // lecture 과목 5가지 확인
        assertEquals(5, allLectures.size)
        assertEquals(40, allUsers.size)
    }

    @Test
    fun ` 동시성 이슈 - 동일한 특강을 40명이 신청했을때 30명만 성공하는 것을 검증하는 테스트 `() {


        // Thread 준비
        val threadCount = 40
        val latch = CountDownLatch(threadCount)
        val executor = Executors.newFixedThreadPool(threadCount)
        // 성공/실패 카운트
        val successCount = AtomicInteger(0)
        val failureCount = AtomicInteger(0)

        // 동시성 테스트
        applyRequests.forEach { request ->
            executor.execute {
                latch.countDown()
                latch.await() // 모든 스레드가 준비될 때까지 대기

                try {
                    applyLectureService.applyUserByRequest(request)
                    successCount.incrementAndGet()
                } catch (ex: Exception) {
                    failureCount.incrementAndGet()
                }

            }
        }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)

        // 검증
        assertThat(successCount.get()).isEqualTo(30)
        assertThat(failureCount.get()).isEqualTo(10)


    }


    @Test
    fun `동일한 유저 정보로 같은 특강을 5번 신청했을 때, 1번만 성공하는 것을 검증하는 테스트 `() {

        val request = ApplyLectureRequest(
            userId = "testUser2",
            "Spring Framework",
            applicationDate = "2025-01-01 16:30:00"
        )


        // 2) 성공/실패 카운트
        val successCount = AtomicInteger(0)
        val failureCount = AtomicInteger(0)

        // 3) 5번 반복: 1초 간격으로 신청 시도
        repeat(5) { attempt ->
            try {
                applyLectureService.applyUserByRequest(request)
                successCount.incrementAndGet()
            } catch (ex: Exception) {
                failureCount.incrementAndGet()
            }
        }

        assertThat(successCount.get()).isEqualTo(1)
        assertThat(failureCount.get()).isEqualTo(4)

    }
}