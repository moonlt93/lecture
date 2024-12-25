package com.my.sparta.lecture.controller.`interface`.request

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Data

@Data // 내가 어떤 클래스를 몇시에 신청할거야 .
class ApplyLectureRequest(
    val userId: String,
    val lectureId: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val applicationDate: String,
) {

}