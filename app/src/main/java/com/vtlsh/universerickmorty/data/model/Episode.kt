package com.vtlsh.universerickmorty.data.model

import android.os.Parcelable
import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import kotlinx.android.parcel.Parcelize

private val klaxon = Klaxon()

@Parcelize
class Episode(
    val id: Long,
    val name: String,

    @Json(name = "air_date")
    val airDate: String,

    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) : Parcelable {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Episode>(json)
    }
}