package com.example.characterviewerapplication.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Data class for character response
 */
data class CharacterResponse(
    @SerializedName("RelatedTopics") val relatedTopics: List<CharacterModel>,
)

