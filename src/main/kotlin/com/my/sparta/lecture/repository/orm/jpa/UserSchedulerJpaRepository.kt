package com.my.sparta.lecture.repository.orm.jpa

import com.my.sparta.lecture.domain.entity.UserScheduler
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface UserSchedulerJpaRepository : JpaRepository<UserScheduler, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
        """
        SELECT us 
        FROM UserScheduler us 
        WHERE us.scheduler.id = :schedulerId AND us.users.id = :userId
    """
    )
    fun findByLectureIdAndUserId(schedulerId: String, userId: String): UserScheduler?

    @Query("select uc from UserScheduler as uc where uc.users.id = :userId")
    fun findByUserId(userId: String): List<UserScheduler>

}
