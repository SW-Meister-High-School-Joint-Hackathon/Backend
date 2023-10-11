package com.hackton.backend.dto.response

data class QueryMonsterInfoResponse(
    val blueKillSpecific: List<String>,
    val redKillSpecific: List<String>,
    val blueKillCount: Int,
    val redKillCount: Int,
)
