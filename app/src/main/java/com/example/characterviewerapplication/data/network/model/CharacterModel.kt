package com.example.characterviewerapplication.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data class for response object
 */
@Parcelize
data class CharacterModel(
    @SerializedName("Text") val text: String,
    @SerializedName("Icon") val Icon: MyIcon,
) : Parcelable

@Parcelize
data class MyIcon(
    @SerializedName("URL") val url: String,
) : Parcelable