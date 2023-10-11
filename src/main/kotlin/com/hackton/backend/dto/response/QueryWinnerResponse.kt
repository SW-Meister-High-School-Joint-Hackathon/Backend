package com.hackton.backend.dto.response

data class QueryWinnerResponse(
    val minute: Int,
    val second: Int,
    val winningTeam: String,
)
