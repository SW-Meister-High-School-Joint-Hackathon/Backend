package com.hackton.backend.service

import com.hackton.backend.common.getFrame
import com.hackton.backend.dto.response.QueryWinnerResponse
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class QueryWinnerService {

    fun getWinnerInfo(): QueryWinnerResponse {
        val eventListList = getFrame().findValues("events")
        var minute = 0
        var second = 0
        var winningTeam = "Blue"

        for (eventList in eventListList) {
            for (event in eventList) {
                val isGameEndEvent = event.findValue("type").asText() == "GAME_END"
                if (isGameEndEvent) {
                    val timestamp = event.findValue("timestamp").asLong()
                    minute = TimeUnit.MILLISECONDS.toMinutes(timestamp).toInt()
                    second = TimeUnit.MILLISECONDS.toSeconds(timestamp).toInt() % 60
                    val isWinningRed = event.findValue("winningTeam").asText() == "200"
                    if (isWinningRed) {
                        winningTeam = "Red"
                    }
                }
            }
        }

        return QueryWinnerResponse(
            minute = minute,
            second = second,
            winningTeam = winningTeam,
        )
    }
}
