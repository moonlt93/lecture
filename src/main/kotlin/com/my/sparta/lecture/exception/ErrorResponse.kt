package com.my.sparta.lecture.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.web.ErrorResponse
import java.time.LocalDateTime

data class ErrorResponse(
    val message: String,
    val errorCode: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
) : ErrorResponse {

    override fun getStatusCode(): HttpStatusCode {
        return HttpStatus.valueOf(errorCode)
    }

    override fun getBody(): ProblemDetail {
        return ProblemDetail.forStatusAndDetail(this.statusCode, this.message)
    }
}