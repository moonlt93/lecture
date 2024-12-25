package com.my.sparta.lecture.domain.entity

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Getter
@Entity
@Table(name = "user_schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class UserScheduler(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_scheduler_id")
    var userSchedulerId: Long = 0L,

    @Column(name = "lecture_id", nullable = false)
    var lectureId: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var users: Users,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduler_id", nullable = false)
    var scheduler: Scheduler,

    @Embedded
    var initDate: InitDate = InitDate( // 기본 생성
        createAt = LocalDateTime.now()
    )

) {


    // 보조 생성자
    constructor(lectureId: String, users: Users, scheduler: Scheduler) :
            this(
                lectureId = lectureId,
                users = users,
                scheduler = scheduler,
                initDate = InitDate(
                    createAt = LocalDateTime.now()
                )
            )

    fun addUser(user: Users) {
        this.users = user
        user.userScheduler.add(this)
    }

    // 양방향 관계 설정 (Scheduler와 연결)
    fun addScheduler(scheduler: Scheduler) {
        this.scheduler = scheduler
        scheduler.userScheduler.add(this)
    }


    companion object {
        fun create(lectureId: String, user: Users, scheduler: Scheduler): UserScheduler {
            return UserScheduler(
                lectureId = lectureId,
                users = user,
                scheduler = scheduler,
                initDate = InitDate(
                    createAt = LocalDateTime.now(),
                    updateAt = LocalDateTime.now()
                )
            )
        }
    }



    @Embeddable
    data class InitDate(

        @Column(name = "create_at", updatable = false, nullable = false)
        val createAt: LocalDateTime, // 생성 시 한 번만 설정, 이후 변경 불가

        @Column(name = "update_at")
        var updateAt: LocalDateTime? = null // 수정 시에만 갱신
    )




}