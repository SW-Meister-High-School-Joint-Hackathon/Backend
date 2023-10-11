package com.hackton.backend.service

import com.hackton.backend.common.getFrame
import com.hackton.backend.dto.response.QueryInhibitorBuildingInfoResponse
import org.springframework.stereotype.Service

@Service
class QueryInhibitorBuildingInfoService {

    fun getInhibitorBuildingInfo(timeStamp: Int): QueryInhibitorBuildingInfoResponse {
        val eventListList = getFrame().findValues("events")
        var blueKillCountInhibitorBuilding = 0
        var redKillCountInhibitorBuilding = 0

        for (eventList in eventListList) {
            for (event in eventList) {
                val isBuildingKillEvent = event.findValue("type").asText() == "BUILDING_KILL"
                val isBeforeRequestTimestamp = event.findValue("timestamp").asInt() <= timeStamp
                if (isBuildingKillEvent && isBeforeRequestTimestamp) {
                    val isInhibitorBuilding = event.findValue("buildingType").asText() == "INHIBITOR_BUILDING"
                    if (isInhibitorBuilding) {
                        val teamId = event.findValue("teamId").asInt()
                        if (teamId == 100) {
                            blueKillCountInhibitorBuilding += 1
                        } else {
                            redKillCountInhibitorBuilding += 1
                        }
                    }
                }
            }
        }

        return QueryInhibitorBuildingInfoResponse(
            blueKillCountInhibitorBuilding = blueKillCountInhibitorBuilding,
            redKillCountInhibitorBuilding = redKillCountInhibitorBuilding,
        )
    }
}
