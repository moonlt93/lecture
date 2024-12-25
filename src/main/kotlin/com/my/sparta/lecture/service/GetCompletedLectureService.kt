package com.my.sparta.lecture.service

import com.my.sparta.lecture.domain.entity.Lecture
import com.my.sparta.lecture.domain.entity.Scheduler
import com.my.sparta.lecture.repository.LectureRepository
import com.my.sparta.lecture.repository.SchedulerRepository
import com.my.sparta.lecture.repository.UserSchedulerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetCompletedLectureService(
    private val schedulerRepository: SchedulerRepository,
    private val userSchedulerRepository: UserSchedulerRepository,
    private val lectureRepository: LectureRepository,
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Transactional(readOnly = true)
    fun getAvailableLectures(): List<Scheduler> {

        val lectures = schedulerRepository.getSchedulerByStatus();

        if (lectures.isEmpty()) {
            logger.info("조회 시 모든 특강이 마감되었습니다.")
        }

        return lectures;

    }

    @Transactional(readOnly = true)
    fun getSelectedLectures(userId: String): List<Lecture> {

        val userLectureIds = userSchedulerRepository.getClosedLectureIdByRegisteredUserId(userId)
        val selectedLectures = lectureRepository.getSpecificLectures(userLectureIds)

        return selectedLectures;
    }
}
