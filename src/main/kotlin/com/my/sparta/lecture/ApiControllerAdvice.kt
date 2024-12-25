package com.my.sparta.lecture

import com.my.sparta.lecture.exception.ErrorResponse
import jakarta.persistence.EntityExistsException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(EntityExistsException::class)
    fun handleException(e: EntityExistsException): ResponseEntity<CustomErrorResponse> {
        val errorResponse = e.message?.let {
            CustomErrorResponse(
                message = it,
                errorCode = HttpStatus.valueOf(500).name,
            )
        }
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<CustomErrorResponse> {
        return ResponseEntity(
            CustomErrorResponse("서버 에러가 발생하였습니다.", "500"),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }


    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleException(e: DataIntegrityViolationException): ResponseEntity<CustomErrorResponse> {
        val errorResponse = e.message?.let {
            CustomErrorResponse(
                message = it,
                errorCode = HttpStatus.valueOf(500).name,
            )
        }
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

