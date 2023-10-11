package com.hackton.backend.service

import com.hackton.backend.dto.response.QueryChampionInfoResponse
import com.hackton.backend.dto.response.QueryChampionInfoResponse.PersonInfo
import com.hackton.backend.dto.response.QueryChampionInfoResponse.TeamInfo
import com.hackton.backend.common.getFrame
import org.springframework.stereotype.Service

@Service
class QueryChampionInfoService {

    fun getChampionInfo(): QueryChampionInfoResponse {
        val championList = getFrame().findValues("participantFrames")[29]

        val personInfoList = mutableListOf<PersonInfo>()

        val blueTeam = TeamInfo(
            teamName = "Blue",
            teamTotalGold = 0,
            teamCurrentGold = 0,
            teamTotalDamageDone = 0,
            teamTotalDamageTaken = 0,
            teamTotalMinionKill = 0,
        )
        val redTeam = TeamInfo(
            teamName = "Red",
            teamTotalGold = 0,
            teamCurrentGold = 0,
            teamTotalDamageDone = 0,
            teamTotalDamageTaken = 0,
            teamTotalMinionKill = 0,
        )

        for ((i, champion) in championList.withIndex()) {
            val personId = i + 1
            val totalGold = champion.findValue("totalGold").asInt()
            val currentGold = champion.findValue("currentGold").asInt()
            val totalDamageDone = champion.findValue("damageStats")["totalDamageDoneToChampions"].asInt()
            val totalDamageTaken = champion.findValue("damageStats")["totalDamageTaken"].asInt()
            val totalMinionKill = champion.findValue("minionsKilled").asInt()

            personInfoList.add(
                PersonInfo(
                    personId = personId,
                    totalGold = totalGold,
                    currentGold = currentGold,
                    totalDamageDone = totalDamageDone,
                    totalDamageTaken = totalDamageTaken,
                    totalMinionKill = totalMinionKill,
                )
            )

            if (personId <= 5) {
                blueTeam.apply {
                    teamTotalGold += totalGold
                    teamCurrentGold += currentGold
                    teamTotalDamageDone += totalDamageDone
                    teamTotalDamageTaken += totalDamageTaken
                    teamTotalMinionKill += totalMinionKill
                }
            } else {
                redTeam.apply {
                    teamTotalGold += totalGold
                    teamCurrentGold += currentGold
                    teamTotalDamageDone += totalDamageDone
                    teamTotalDamageTaken += totalDamageTaken
                    teamTotalMinionKill += totalMinionKill
                }
            }
        }

        return QueryChampionInfoResponse(
            personInfoList = personInfoList,
            teamInfoList = listOf(blueTeam, redTeam),
        )
    }
}
