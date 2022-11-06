// To parse the JSON, install jackson-module-kotlin and do:
//
//   val leaguesModel = LeaguesModel.fromJson(jsonString)

package ru.porcupine.footballplayers.models.player

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.module.kotlin.*


@Suppress("UNCHECKED_CAST")
private fun <T> ObjectMapper.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonNode) -> T, toJson: (T) -> String, isUnion: Boolean = false) = registerModule(SimpleModule().apply {
	addSerializer(k.java as Class<T>, object : StdSerializer<T>(k.java as Class<T>) {
		override fun serialize(value: T, gen: JsonGenerator, provider: SerializerProvider) = gen.writeRawValue(toJson(value))
	})
	addDeserializer(k.java as Class<T>, object : StdDeserializer<T>(k.java as Class<T>) {
		override fun deserialize(p: JsonParser, ctxt: DeserializationContext) = fromJson(p.readValueAsTree())
	})
})

val mapper = jacksonObjectMapper().apply {
	propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
	setSerializationInclusion(JsonInclude.Include.NON_NULL)
	convert(Position::class, { Position.fromValue(it.asText()) }, { "\"${it.value}\"" })
}

data class PlayersModel (
	val get: String? = null,
	val parameters: Parameters? = null,
	val errors: List<Any?>? = null,
	val results: Long? = null,
	val paging: Paging? = null,
	val response: List<Response>? = null
) {
	fun toJson() = mapper.writeValueAsString(this)

	companion object {
		fun fromJson(json: String) = mapper.readValue<PlayersModel>(json)
	}
}

data class Paging (
	val current: Long? = null,
	val total: Long? = null
)

data class Parameters (
	val league: String? = null,
	val season: String? = null
)

data class Response (
	val player: Player? = null,
	val statistics: List<Statistic>? = null
)

data class Player (
	val id: Long? = null,
	val name: String? = null,
	val firstname: String? = null,
	val lastname: String? = null,
	val age: Long? = null,
	val birth: Birth? = null,
	val nationality: String? = null,
	val height: String? = null,
	val weight: String? = null,
	val injured: Boolean? = null,
	val photo: String? = null
)

data class Birth (
	val date: String? = null,
	val place: String? = null,
	val country: String? = null
)

data class Statistic (
	val team: Team? = null,
	val league: League? = null,
	val games: Games? = null,
	val substitutes: Substitutes? = null,
	val shots: Shots? = null,
	val goals: Goals? = null,
	val passes: Passes? = null,
	val tackles: Tackles? = null,
	val duels: Duels? = null,
	val dribbles: Dribbles? = null,
	val fouls: Fouls? = null,
	val cards: Cards? = null,
	val penalty: Penalty? = null
)

data class Cards (
	val yellow: Long? = null,
	val yellowred: Long? = null,
	val red: Long? = null
)

data class Dribbles (
	val attempts: Long? = null,
	val success: Long? = null,
	val past: Any? = null
)

data class Duels (
	val total: Long? = null,
	val won: Long? = null
)

data class Fouls (
	val drawn: Long? = null,
	val committed: Long? = null
)

data class Games (
	val appearences: Long? = null,
	val lineups: Long? = null,
	val minutes: Long? = null,
	val number: Any? = null,
	val position: Position? = null,
	val rating: String? = null,
	val captain: Boolean? = null
)

enum class Position(val value: String) {
	Attacker("Attacker"),
	Defender("Defender"),
	Goalkeeper("Goalkeeper"),
	Midfielder("Midfielder");

	companion object {
		fun fromValue(value: String): Position = when (value) {
			"Attacker"   -> Attacker
			"Defender"   -> Defender
			"Goalkeeper" -> Goalkeeper
			"Midfielder" -> Midfielder
			else         -> throw IllegalArgumentException()
		}
	}
}

data class Goals (
	val total: Long? = null,
	val conceded: Long? = null,
	val assists: Long? = null,
	val saves: Long? = null
)

data class League (
	val id: Long? = null,
	val name: String? = null,
	val country: String? = null,
	val logo: String? = null,
	val flag: String? = null,
	val season: Long? = null
)

data class Passes (
	val total: Long? = null,
	val key: Long? = null,
	val accuracy: Long? = null
)

data class Penalty (
	val won: Any? = null,
	val commited: Any? = null,
	val scored: Long? = null,
	val missed: Long? = null,
	val saved: Long? = null
)

data class Shots (
	val total: Long? = null,
	val on: Long? = null
)

data class Substitutes (
	@get:JsonProperty("in")@field:JsonProperty("in")
	val substitutesIn: Long? = null,

	val out: Long? = null,
	val bench: Long? = null
)

data class Tackles (
	val total: Long? = null,
	val blocks: Long? = null,
	val interceptions: Long? = null
)

data class Team (
	val id: Long? = null,
	val name: String? = null,
	val logo: String? = null
)
