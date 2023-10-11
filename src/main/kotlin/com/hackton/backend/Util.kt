package com.hackton.backend

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import java.io.File

fun getFile(): File = ClassPathResource("EMH_LSB_KT_set1.json").file

fun getFrame(): JsonNode = jacksonObjectMapper().readValue<JsonNode>(getFile())["frames"]
