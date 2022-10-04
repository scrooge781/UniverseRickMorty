package com.vtlsh.universerickmorty.business

import com.vtlsh.universerickmorty.data.remote.ApiService
import javax.inject.Inject

class NetworkUseCase @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getCharacter() = apiService.getCharacter()

    suspend fun getEpisode(episodeId: String) = apiService.getEpisode(episodeId)

}