package com.my.sparta.lecture.repository.impl

import com.my.sparta.lecture.domain.entity.UserScheduler
import com.my.sparta.lecture.repository.UserSchedulerRepository
import com.my.sparta.lecture.repository.orm.jpa.UserSchedulerJpaRepository
import jakarta.persistence.EntityExistsException
import org.springframework.stereotype.Repository

@Repository
class UserSchedulerRepositoryImpl(
    private val userSchedulerJpaRepository: UserSchedulerJpaRepository
) : UserSchedulerRepository {

    override fun saveUserScheduler(entity: UserScheduler): UserScheduler {

        return userSchedulerJpaRepository.save(entity)

    }

    override fun getUserSchedulerWithSchedulerId(schedulerId: String, userId: String): UserScheduler {

        return userSchedulerJpaRepository.findByLectureIdAndUserId(schedulerId, userId)
            .orElseThrow {

                throw EntityExistsException("이미 등록된 수강생입니다 : 강의ID = $schedulerId , 수강생ID = $userId ")

            }
    }
}