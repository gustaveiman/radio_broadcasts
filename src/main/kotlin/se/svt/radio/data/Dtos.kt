package se.svt.radio.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.util.StdConverter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.core.ResponseDeserializable
import se.svt.radio.domain.Broadcast
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

const val NUMBERS_ONLY = "[^0-9]"

class SRDateConverter: StdConverter<String, Instant>() {
    override fun convert(value: String): Instant {
        val unixTimestamp: String = NUMBERS_ONLY.toRegex().replace(value, "")
        return Instant.ofEpochMilli(unixTimestamp.toLong())
    }
}

data class BroadcastDto(
    val title: String,
    val description: String,
    @JsonProperty("broadcastdateutc")
    @JsonDeserialize(converter = SRDateConverter::class)
    val broadcastDateUtc: Instant
) {
    fun toModel(): Broadcast = Broadcast(
        title,
        description,
        LocalDateTime.ofInstant(broadcastDateUtc, ZoneId.of("Europe/Stockholm")))
}

data class BroadcastsResponse(val name: String, val broadcasts: List<BroadcastDto>)

object BroadcastResponseDeserializer : ResponseDeserializable<BroadcastsResponse> {
    override fun deserialize(content: String) =
        jacksonObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .readValue<BroadcastsResponse>(content)
}

data class ProgramDto(
    val id: Int, val name: String, val description: String
)

data class ProgramsResponse(val programs: List<ProgramDto>)

object ProgramResponseDeserializer : ResponseDeserializable<ProgramsResponse> {
    override fun deserialize(content: String) =
        jacksonObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .readValue<ProgramsResponse>(content)
}