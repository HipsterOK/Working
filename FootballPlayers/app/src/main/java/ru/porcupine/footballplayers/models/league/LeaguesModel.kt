package ru.porcupine.footballplayers.models.league

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue


@Suppress("UNCHECKED_CAST")
private fun <T> ObjectMapper.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonNode) -> T, toJson: (T) -> String, isUnion: Boolean = false) = registerModule(SimpleModule().apply {
    addSerializer(k.java as Class<T>, object : StdSerializer<T>(k.java as Class<T>) {
        override fun serialize(value: T, gen: JsonGenerator, provider: SerializerProvider) = gen.writeRawValue(toJson(value))
    })
    addDeserializer(k.java as Class<T>, object : StdDeserializer<T>(k.java as Class<T>) {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext) = fromJson(p.readValueAsTree())
    })
})

val mapper1 = jacksonObjectMapper().apply {
    propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
    setSerializationInclusion(JsonInclude.Include.NON_NULL)
}

data class LeaguesModel (
    val get: String? = null,
    val parameters: Parameters? = null,
    val errors: List<Any?>? = null,
    val results: Long? = null,
    val paging: Paging? = null,
    val response: List<Response>? = null
) {
    fun toJson() = mapper1.writeValueAsString(this)

    companion object {
        fun fromJson(json: String) = mapper1.readValue<LeaguesModel>(json)
    }
}

data class Paging (
    val current: Long? = null,
    val total: Long? = null
)

data class Parameters (
    val season: String? = null
)

data class Response (
    val league: League? = null,
    val country: Country? = null,
    val seasons: List<Season>? = null
)

data class Country (
    val name: String? = null,
    val code: String? = null,
    val flag: String? = null
)

data class League (
    val id: Long? = null,
    val name: String? = null,
    val type: String? = null,
    val logo: String? = null
)

data class Season (
    val year: Long? = null,
    val start: String? = null,
    val end: String? = null,
    val current: Boolean? = null,
    val coverage: Coverage? = null
)

data class Coverage (
    val fixtures: Fixtures? = null,
    val standings: Boolean? = null,
    val players: Boolean? = null,

    @get:JsonProperty("top_scorers")@field:JsonProperty("top_scorers")
    val topScorers: Boolean? = null,

    @get:JsonProperty("top_assists")@field:JsonProperty("top_assists")
    val topAssists: Boolean? = null,

    @get:JsonProperty("top_cards")@field:JsonProperty("top_cards")
    val topCards: Boolean? = null,

    val injuries: Boolean? = null,
    val predictions: Boolean? = null,
    val odds: Boolean? = null
)

data class Fixtures (
    val events: Boolean? = null,
    val lineups: Boolean? = null,

    @get:JsonProperty("statistics_fixtures")@field:JsonProperty("statistics_fixtures")
    val statisticsFixtures: Boolean? = null,

    @get:JsonProperty("statistics_players")@field:JsonProperty("statistics_players")
    val statisticsPlayers: Boolean? = null
)
