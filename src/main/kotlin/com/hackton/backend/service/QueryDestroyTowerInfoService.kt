package com.hackton.backend.service

import com.hackton.backend.common.getFrame
import com.hackton.backend.dto.response.QueryDestroyTowerInfoResponse
import org.springframework.stereotype.Service

@Service
class QueryDestroyTowerInfoService {

    fun getDestroyTowerInfo(timeStamp: Int): QueryDestroyTowerInfoResponse {
        val eventListList = getFrame().findValues("events")
        var blueDestroyTowerCount = 0
        var redDestroyTowerCount = 0

        for (eventList in eventListList) {
            for (event in eventList) {
                val isBuildingKillEvent = event.findValue("type").asText() == "BUILDING_KILL"
                val isBeforeRequestTimestamp = event.findValue("timestamp").asInt() <= timeStamp
                if (isBuildingKillEvent && isBeforeRequestTimestamp) {
                    val isBuildingTypeTower = event.findValue("buildingType").asText() == "TOWER_BUILDING"
                    if (isBuildingTypeTower) {
                        val teamId = event.findValue("teamId").asInt()
                        if (teamId == 100) {
                            redDestroyTowerCount += 1
                        } else {
                            blueDestroyTowerCount += 1
                        }
                    }
                }
            }
        }

        return QueryDestroyTowerInfoResponse(
            blueDestroyTowerCount = blueDestroyTowerCount,
            redDestroyTowerCount = redDestroyTowerCount,
        )
    }
}
