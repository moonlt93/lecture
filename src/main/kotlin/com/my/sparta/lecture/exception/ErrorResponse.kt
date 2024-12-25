package com.my.sparta.lecture.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.web.ErrorResponse
import java.time.LocalDateTime


data class CustomErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now()
) : ErrorResponse {

    private lateinit var problemDetail: ProblemDetail

    constructor(message: String, errorCode: String) : this() {
        problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, message).apply {
            title = "Internal Server Error"
        }
    }

    override fun getStatusCode(): HttpStatusCode {
        return HttpStatus.INTERNAL_SERVER_ERROR
    }

    override fun getBody(): ProblemDetail {
        return problemDetail
    }
}