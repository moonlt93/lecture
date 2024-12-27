package com.my.sparta.lecture.repository.orm.jpa

import com.my.sparta.lecture.application.domain.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureJpaRepository : JpaRepository<Lecture, String> {

}
