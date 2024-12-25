package com.my.sparta.lecture.repository.orm.jpa

import com.my.sparta.lecture.domain.entity.UserScheduler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserSchedulerJpaRepository : JpaRepository<UserScheduler, Long> {

    @Query("""
        SELECT us 
        FROM UserScheduler us 
        WHERE us.lectureId = :lectureId AND us.users.id = :userId
    """)
    fun findByLectureIdAndUserId(lectureId: String, userId: String): Optional<UserScheduler>
}
