package com.hackton.backend.common

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.hackton.backend.common.error.FindValueNotFoundException
import org.springframework.core.io.ClassPathResource
import java.io.File

fun getFrame(): JsonNode = jacksonObjectMapper().readValue<JsonNode>(getFile())["frames"]

fun JsonNode.getValue(key: String): JsonNode =
    try {
        this.findValue(key)
    } catch (_: NullPointerException) {
        throw FindValueNotFoundException
    }

private fun getFile(): File = ClassPathResource("EMH_LSB_KT_set1.json").file
