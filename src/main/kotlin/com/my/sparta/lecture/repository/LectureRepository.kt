package com.my.sparta.lecture.repository

import com.my.sparta.lecture.domain.entity.Lecture

interface LectureRepository {

    fun getSpecificLectures(lectureIds: List<String>): List<Lecture>

}
