package com.vtlsh.universerickmorty.data.remote

import com.vtlsh.universerickmorty.data.model.Episode
import com.vtlsh.universerickmorty.data.model.ItemRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/character")
    suspend fun getCharacter(): Response<ItemRemote>

    @GET("api/episode/{id}")
    suspend fun getEpisode(@Path("id") episodeId: String): Response<Episode>

}