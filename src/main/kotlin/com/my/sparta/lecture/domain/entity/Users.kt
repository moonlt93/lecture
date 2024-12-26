package com.my.sparta.lecture.domain.entity

import com.my.sparta.lecture.domain.entity.UserScheduler.InitDate
import jakarta.persistence.*
import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Users(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String,

    @Column(nullable = false)
    var username: String = "",

    @OneToMany(mappedBy = "users")
    var userScheduler: MutableSet<UserScheduler> = mutableSetOf(),

    @Embedded
    var initDate: InitDate = InitDate( // 기본 생성
        createAt = LocalDateTime.now()
    )

) {

    constructor(userId: String) : this(
        id = userId,
        initDate = InitDate(
            updateAt = LocalDateTime.now()
        )
    )

    @Embeddable
    data class InitDate(

        @Column(name = "create_at", updatable = false, nullable = false)
        val createAt: LocalDateTime = LocalDateTime.now(), // 기본값 추가

        @Column(name = "update_at")
        var updateAt: LocalDateTime? = null
    )
}