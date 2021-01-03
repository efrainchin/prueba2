package com.efrain.pruebaalbo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efrain.pruebaalbo.utils.Constants.Companion.TABLE_BEERS
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Carlos chin on 31/12/2020.
 * Correo: efrain@sunwise.io
 */

@Entity (tableName = TABLE_BEERS)
data class Beers(

    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("image_url")
    val imageUrl: String
): Serializable