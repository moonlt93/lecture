package com.my.sparta.lecture.repository.orm.jpa

import com.my.sparta.lecture.domain.entity.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UsersJpaRepository : JpaRepository<Users, String> {
}