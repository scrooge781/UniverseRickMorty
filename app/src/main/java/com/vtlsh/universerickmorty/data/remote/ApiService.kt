package com.vtlsh.universerickmorty.data.remote

import com.vtlsh.universerickmorty.data.model.ItemRemote
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/character")
    suspend fun getCharacter(): Response<ItemRemote>

}