package com.hackton.backend.dto.response

data class QueryChampionInfoResponse(
    val personInfoList: List<PersonInfo>,
    val teamInfoList: List<TeamInfo>,
) {
    data class PersonInfo(
        val personId: Int,
        val totalGold: Int,
        val currentGold: Int,
        val totalDamageDone: Int,
        val totalDamageTaken: Int,
        val totalMinionKill: Int,
    )

    data class TeamInfo(
        val teamName: String, // Blue, Red 구분
        var teamTotalGold: Int,
        var teamCurrentGold: Int,
        var teamTotalDamageDone: Int,
        var teamTotalDamageTaken: Int,
        var teamTotalMinionKill: Int,
    )
}
