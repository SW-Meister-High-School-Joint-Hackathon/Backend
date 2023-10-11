package com.hackton.backend.service

import com.hackton.backend.common.getFrame
import com.hackton.backend.dto.response.QueryMonsterInfoResponse
import org.springframework.stereotype.Service

@Service
class QueryMonsterInfoService {

    fun getMonsterInfo(timeStamp: Int): QueryMonsterInfoResponse? {
        val monsterInfoListList = getFrame().findValues("events")

        val blueKillSpecific = mutableListOf<String>()
        val redKillSpecific = mutableListOf<String>()
        var blueKillCount = 0
        var redKillCount = 0

        for (monsterInfoList in monsterInfoListList) {
            for (monsterInfo in monsterInfoList) {
                val isEliteMonsterKill = monsterInfo.findValue("type").asText() == "ELITE_MONSTER_KILL"
                val isBeforeRequestTimestamp = monsterInfo.findValue("timestamp").asInt() <= timeStamp
                if (isEliteMonsterKill && isBeforeRequestTimestamp) {
                    val killerTeam = monsterInfo.findValue("killerTeamId").asInt()
                    var monsterType = monsterInfo.findValue("monsterType").asText()
                    if (monsterType == "DRAGON") {
                        monsterType = monsterInfo.findValue("monsterSubType").asText()
                    }
                    if (killerTeam == 100) {
                        blueKillCount += 1
                        blueKillSpecific.add(monsterType)
                    } else {
                        redKillCount += 1
                        redKillSpecific.add(monsterType)
                    }
                }
            }
        }

        return QueryMonsterInfoResponse(
            blueKillSpecific = blueKillSpecific,
            redKillSpecific = redKillSpecific,
            blueKillCount = blueKillCount,
            redKillCount = redKillCount,
        )
    }
}
