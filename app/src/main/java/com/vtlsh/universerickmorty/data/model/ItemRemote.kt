package com.vtlsh.universerickmorty.data.model


import com.beust.klaxon.*
import com.vtlsh.universerickmorty.R
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
    this.converter(object: Converter {
        @Suppress("UNCHECKED_CAST")
        override fun toJson(value: Any)        = toJson(value as T)
        override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
        override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
    })

private val klaxon = Klaxon()
    .convert(Gender::class,  { Gender.fromValue(it.string!!) },  { "\"${it.value}\"" })
    .convert(Species::class, { Species.fromValue(it.string!!) }, { "\"${it.value}\"" })
    .convert(Status::class,  { Status.fromValue(it.string!!) },  { "\"${it.value}\"" })

data class ItemRemote (
    val info: Info,
    val results: List<Result>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<ItemRemote>(json)
    }
}

data class Info (
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: Any? = null
)

@Parcelize
data class Result (
    val id: Long,
    val name: String,
    val status: Status,
    val species: Species,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) : Parcelable

enum class Gender(val value: String) {
    Female("Female"),
    Male("Male"),
    Unknown("unknown");

    companion object {
        fun fromValue(value: String): Gender = when (value) {
            "Female"  -> Female
            "Male"    -> Male
            "unknown" -> Unknown
            else      -> throw IllegalArgumentException()
        }
    }
}

@Parcelize
data class Location (
    val name: String,
    val url: String
) : Parcelable

enum class Species(val value: String) {
    Alien("Alien"),
    Human("Human");

    companion object {
        fun fromValue(value: String): Species = when (value) {
            "Alien" -> Alien
            "Human" -> Human
            else    -> throw IllegalArgumentException()
        }
    }
}

enum class Status(val value: String, val color: Int, val gradient: Int) {
    Alive("Alive", R.color.alive, R.drawable.alive_gradient),
    Dead("Dead", R.color.dead, R.drawable.dead_gradient),
    unknown("Unknown", R.color.unknown, R.drawable.unknown_gradient);

    companion object {
        fun fromValue(value: String): Status = when (value) {
            "Alive"   -> Alive
            "Dead"    -> Dead
            "Unknown" -> unknown
            else      -> throw IllegalArgumentException()
        }
    }
}