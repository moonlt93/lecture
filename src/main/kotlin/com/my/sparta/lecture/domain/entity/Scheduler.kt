package com.my.sparta.lecture.domain.entity

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Getter
@Entity
@Table(name = "lecture_schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class Scheduler constructor(

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String,

    var lectureId: String,

    @Column(name = "create_at")
    var createAt: LocalDateTime,
    @Column(name = "update_at")
    var updateAt: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    var day: Days,

    @Column(name = "capacity")
    var capacity: Int,

    @OneToMany(mappedBy = "scheduler")
    var userScheduler: MutableSet<UserScheduler> = mutableSetOf(),

    @Embedded
    val duration: Duration,

    ) {

    @Getter
    enum class Days(
        var value: String,
    ) {

        MONDAY("월요일"),
        TUESDAY("화요일"),
        WEDNESDAY("수요일"),
        THURSDAY("목요일"),
        FRIDAY("금요일"),
        SATURDAY("토요일"),
        SUNDAY("일요일");

    }

    fun minusCapacity() {
        check(this.capacity <= 0) {
            "수강 인원을 초과 하엿습니다. 현재 남은 수강인원 =$capacity "
        }
        this.capacity -= 1;
    }

    @Embeddable
    data class Duration(
        val targetDate: LocalDate,
        val startTime: LocalTime,
        val endTime: LocalTime
    )

}

