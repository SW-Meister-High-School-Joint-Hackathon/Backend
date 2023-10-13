package com.hackton.backend.service

import com.hackton.backend.dto.response.TimestampResponse
import org.springframework.stereotype.Service

@Service
class QueryTimestampService {

    fun getTimestamp(minute: Int, second: Int) = TimestampResponse(
        (minute * 60L * 1000 + second * 1000).toInt()
    )
}
