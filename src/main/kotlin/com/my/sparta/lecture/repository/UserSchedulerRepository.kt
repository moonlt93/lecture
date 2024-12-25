package com.my.sparta.lecture.repository

import com.my.sparta.lecture.domain.entity.UserScheduler

interface UserSchedulerRepository {

    fun saveUserScheduler(entity: UserScheduler): UserScheduler

    fun getUserSchedulerWithSchedulerId(schedulerId: String, userId: String): UserScheduler
}
