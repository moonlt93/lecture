package com.my.sparta.lecture.exception

class CapacityExceededException(val schedulerId: String, val currentCapacity: Int) : RuntimeException(
    "수강 인원을 초과하였습니다. 현재 남은 수강 인원 = $currentCapacity"
)