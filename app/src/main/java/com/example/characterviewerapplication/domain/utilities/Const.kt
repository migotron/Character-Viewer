package com.example.characterviewerapplication.domain.utilities

import com.example.characterviewerapplication.BuildConfig


/**
 * List of static constants in App
 */
class Const {
    companion object {
        const val ICON_BASE_URL = "https://duckduckgo.com"
        const val BASE_URL: String = BuildConfig.BASE_URL
        const val API_QUERY_PARAM: String = BuildConfig.API_QUERY_PARAM
        const val API_QUERY_PARAM_FORMAT: String = BuildConfig.API_QUERY_PARAM_FORMAT

    }
}