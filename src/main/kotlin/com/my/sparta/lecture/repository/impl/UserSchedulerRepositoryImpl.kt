package com.my.sparta.lecture.repository.impl

import com.my.sparta.lecture.application.domain.entity.UserScheduler
import com.my.sparta.lecture.repository.UserSchedulerRepository
import com.my.sparta.lecture.repository.orm.jpa.UserSchedulerJpaRepository
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Repository

@Repository
class UserSchedulerRepositoryImpl(
    private val userSchedulerJpaRepository: UserSchedulerJpaRepository
) : UserSchedulerRepository {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    override fun saveUserScheduler(entity: UserScheduler): UserScheduler {

        return userSchedulerJpaRepository.save(entity)

    }

    override fun getUserSchedulerWithSchedulerId(schedulerId: String, userId: String): UserScheduler? {

        return userSchedulerJpaRepository.findByLectureIdAndUserId(schedulerId, userId)

    }

    override fun getClosedLectureIdByRegisteredUserId(userId: String): List<String> {

        val userSchedulers = userSchedulerJpaRepository.findByUserId(userId)

        return userSchedulers.map { it.lectureId }
    }
}