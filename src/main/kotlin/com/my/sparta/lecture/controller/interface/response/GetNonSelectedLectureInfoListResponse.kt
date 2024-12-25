package com.my.sparta.lecture.controller.`interface`.response

import com.my.sparta.lecture.domain.entity.Scheduler

class GetNonSelectedLectureInfoListResponse constructor(

    val getSelectedLectureInfoResponse: List<GetSchedulerInfoResponse>

) {
    companion object {

        fun mapToResponseList(schedulers: List<Scheduler>): List<GetSchedulerInfoResponse> {
            return schedulers.stream()
                .map { scheduler ->
                    GetSchedulerInfoResponse.toScheduler(scheduler)
                }
                .toList()
        }

    }

}