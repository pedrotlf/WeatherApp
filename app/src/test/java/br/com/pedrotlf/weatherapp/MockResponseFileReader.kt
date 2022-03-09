package br.com.pedrotlf.weatherapp

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.InputStreamReader


class MockResponseFileReader(path: String) {
    val content: String

    init {
        val reader = InputStreamReader(javaClass.classLoader.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }

    inline fun <reified T> getDesserializedContent() : T  = jacksonObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .readValue(content, T::class.java)
}