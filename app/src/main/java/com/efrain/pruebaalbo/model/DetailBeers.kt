package com.efrain.pruebaalbo.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Carlos chin on 31/12/2020.
 * Correo: efrain@sunwise.io
 */
data class DetailBeers(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("first_brewed")
    val firstBrewed: String,

    @SerializedName("food_pairing")
    val foodPairing: List<String>

)
