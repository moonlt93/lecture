package com.my.sparta.lecture.domain.entity

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Getter
@Entity
@Table(name = "lecture")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class Lecture constructor(

    @Id
    val id: String,

    @Column(name = "lecture_name")
    val lectureName: String,

    @Column(name = "speaker")
    val speaker: String

    ) {


}