package com.example.characterviewerapplication.data.network.service

import com.example.characterviewerapplication.data.network.model.CharacterModel
import com.example.characterviewerapplication.data.network.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  We create one interface class which will be used for declaration of all API calling functions.
 */
interface APIService {
    @GET("/")
    suspend fun getCharactersList(
        @Query("q") q: String,
        @Query("format") format: String,
    ): Response<CharacterResponse>
}