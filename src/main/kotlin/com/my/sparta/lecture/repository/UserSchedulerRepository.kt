package com.my.sparta.lecture.repository

import com.my.sparta.lecture.domain.entity.UserScheduler
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock

interface UserSchedulerRepository {


    fun saveUserScheduler(entity: UserScheduler): UserScheduler

    fun getUserSchedulerWithSchedulerId(schedulerId: String, userId: String): UserScheduler?
    fun getClosedLectureIdByRegisteredUserId(userId: String): List<String>
}
