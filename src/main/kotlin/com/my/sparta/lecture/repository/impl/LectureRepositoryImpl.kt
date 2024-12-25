package com.my.sparta.lecture.repository.impl

import com.my.sparta.lecture.domain.entity.Lecture
import com.my.sparta.lecture.repository.LectureRepository
import com.my.sparta.lecture.repository.orm.jpa.LectureJpaRepository
import org.springframework.stereotype.Repository

@Repository
class LectureRepositoryImpl(
    private val lectureJpaRepository: LectureJpaRepository
) : LectureRepository {

    override fun getSpecificLectures(lectureIds: List<String>): List<Lecture> {

        return lectureJpaRepository.findAllById(lectureIds)

    }
}