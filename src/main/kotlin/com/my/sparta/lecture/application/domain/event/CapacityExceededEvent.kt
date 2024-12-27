package com.my.sparta.lecture.application.domain.event

class CapacityExceededEvent(
    var schedulerId: String,
    var currentCapacity: Int,
)
