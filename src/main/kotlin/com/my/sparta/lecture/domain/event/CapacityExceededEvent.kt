package com.my.sparta.lecture.domain.event

class CapacityExceededEvent(
    var schedulerId: String,
    var currentCapacity: Int,
)
