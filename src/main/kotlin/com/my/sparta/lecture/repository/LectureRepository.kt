package com.my.sparta.lecture.repository

import com.my.sparta.lecture.application.domain.entity.Lecture

interface LectureRepository {

    fun getSpecificLectures(lectureIds: List<String>): List<Lecture>

}
