package com.hackton.backend.controller

import com.hackton.backend.dto.response.QueryChampionInfoResponse
import com.hackton.backend.dto.response.QueryDestroyTowerInfoResponse
import com.hackton.backend.dto.response.QueryInhibitorBuildingInfoResponse
import com.hackton.backend.dto.response.QueryMonsterInfoResponse
import com.hackton.backend.dto.response.QueryWinnerResponse
import com.hackton.backend.service.QueryChampionInfoService
import com.hackton.backend.service.QueryDestroyTowerInfoService
import com.hackton.backend.service.QueryInhibitorBuildingInfoService
import com.hackton.backend.service.QueryMonsterInfoService
import com.hackton.backend.service.QueryWinnerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/data")
@RestController
class DataController(
    private val queryChampionInfoService: QueryChampionInfoService,
    private val queryMonsterInfoService: QueryMonsterInfoService,
    private val queryInhibitorBuildingInfoService: QueryInhibitorBuildingInfoService,
    private val queryWinnerService: QueryWinnerService,
    private val queryDestroyTowerInfoService: QueryDestroyTowerInfoService,
) {
    @GetMapping("/champion")
    fun getChampionInfo(): QueryChampionInfoResponse {
        return queryChampionInfoService.getChampionInfo()
    }

    @GetMapping("/monster")
    fun getMonsterInfo(
        @RequestParam("timeStamp")
        timestamp: Int,
    ): QueryMonsterInfoResponse? {
        return queryMonsterInfoService.getMonsterInfo(timestamp)
    }

    @GetMapping("/inhibitor")
    fun getInhibitorBuildingInfo(
        @RequestParam("timeStamp")
        timestamp: Int,
    ): QueryInhibitorBuildingInfoResponse {
        return queryInhibitorBuildingInfoService.getInhibitorBuildingInfo(timestamp)
    }

    @GetMapping("/winner")
    fun getWinnerInfo(): QueryWinnerResponse {
        return queryWinnerService.getWinnerInfo()
    }

    @GetMapping("/tower")
    fun getTowerInfo(
        @RequestParam("timeStamp")
        timeStamp: Int,
    ): QueryDestroyTowerInfoResponse {
        return queryDestroyTowerInfoService.getDestroyTowerInfo(timeStamp)
    }
}
