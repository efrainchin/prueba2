package com.efrain.pruebaalbo.api

import com.efrain.pruebaalbo.model.Beers
import com.efrain.pruebaalbo.model.DetailBeers
import com.efrain.pruebaalbo.utils.Constants.Companion.BEERS
import com.efrain.pruebaalbo.utils.Constants.Companion.BEERS_BY_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Carlos chin on 31/12/2020.
 * Correo: efrain@sunwise.io
 */

interface BeersApi {

    @GET(BEERS)
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<List<Beers>>

    @GET(BEERS_BY_ID)
    suspend fun getBeersById(
        @Path("id")id: String
    ): List<DetailBeers>
}