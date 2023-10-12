package com.hackton.backend.common

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.hackton.backend.common.error.FindValueNotFoundException
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

fun getFrame(): JsonNode = jacksonObjectMapper().readValue<JsonNode>(getFile())["frames"]

fun JsonNode.getValue(key: String): JsonNode = // TODO: 추후 findValue -> 모두 이 메소드로 수정
    try {
        this.findValue(key)
    } catch (_: NullPointerException) {
        throw FindValueNotFoundException
    }

private fun getFile(): File {
    val tempFile = Files.createTempFile("prefix", "suffix").toFile()
    ClassPathResource("EMH_LSB_KT_set1.json").inputStream.use { inputStream ->
        Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
    }
    return tempFile
}
