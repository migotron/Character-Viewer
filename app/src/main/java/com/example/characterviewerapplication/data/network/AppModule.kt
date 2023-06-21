package com.example.characterviewerapplication.data.network

import com.example.characterviewerapplication.data.network.service.APIService
import com.example.characterviewerapplication.domain.utilities.Const.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * In this theRetrofitInstance() function, we build the retrofit instance for generating API calling.
 * We have to pass the base url of the API.
 */
class AppModule {
    fun theRetrofitInstance(): APIService {
        val API: APIService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
        return API
    }
}
