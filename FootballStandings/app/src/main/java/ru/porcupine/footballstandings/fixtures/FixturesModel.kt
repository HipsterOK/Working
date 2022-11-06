package ru.porcupine.footballstandings.fixtures


data class FixturesModel (
    val get: String? = null,
    val parameters: Parameters? = null,
    val errors: List<Any?>? = null,
    val results: Long? = null,
    val paging: Paging? = null,
    val response: List<Response>? = null
)

data class Parameters (
    val date: String? = null
)

data class Paging (
    val current: Long? = null,
    val total: Long? = null
)

data class Response (
    val fixture: Fixture? = null,
    val league: League? = null,
    val teams: Teams? = null,
    val goals: Goals? = null,
    val score: Score? = null
)

data class Fixture (
    val id: Long? = null,
    val referee: String? = null,
    val timezone: Timezone? = null,
    val date: String? = null,
    val timestamp: Long? = null,
    val periods: Periods? = null,
    val venue: Venue? = null,
    val status: Status? = null
)

data class Periods (
    val first: Long? = null,
    val second: Long? = null
)

data class Status (
    val long: LongEnum? = null,
    val short: ShortEnum? = null,
    val elapsed: Long? = null
)

enum class LongEnum {
    MatchCancelled,
    MatchFinished
}

enum class ShortEnum {
    Canc,
    Ft,
    Pen
}

enum class Timezone {
    UTC
}

data class Venue (
    val id: Long? = null,
    val name: String? = null,
    val city: String? = null
)

data class Goals (
    val home: Long? = null,
    val away: Long? = null
)

data class League (
    val id: Long? = null,
    val name: String? = null,
    val country: String? = null,
    val logo: String? = null,
    val flag: String? = null,
    val season: Long? = null,
    val round: String? = null
)

data class Score (
    val halftime: Goals? = null,
    val fulltime: Goals? = null,
    val extratime: Goals? = null,
    val penalty: Goals? = null
)

data class Teams (
    val home: Away? = null,
    val away: Away? = null
)

data class Away (
    val id: Long? = null,
    val name: String? = null,
    val logo: String? = null,
    val winner: Boolean? = null
)
