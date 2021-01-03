package com.efrain.pruebaalbo.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.MainThread
import com.efrain.pruebaalbo.api.BeersApi
import com.efrain.pruebaalbo.data.PruebAlboDatabase
import com.efrain.pruebaalbo.data.dao.BeersDao
import com.efrain.pruebaalbo.model.Beers
import com.efrain.pruebaalbo.model.DetailBeers
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Carlos chin on 31/12/2020.
 * Correo: efrain@sunwise.io
 */

class BeersRepository @Inject constructor(private val beersApi: BeersApi, private val beersDao: BeersDao) {

    suspend fun getBeers(page: Int, per_page: Int): List<Beers> {
        var list: List<Beers> = listOf()
            withContext(Dispatchers.IO) {
            try {
                val response = beersApi.getBeers(page, per_page)
                if (response.isSuccessful) {
                    response.body()?.let {
                        insertListBeers(it,beersDao)
                        list = it
                        return@withContext
                    }
                    list = beersDao.getAll()
                } else
                    list = beersDao.getAll()
            }catch (e: Exception) {
                list = beersDao.getAll()
            }
        }
        return list
    }

    suspend fun getBeerById(id: String): List<DetailBeers> = withContext(Dispatchers.IO) { beersApi.getBeersById(id)}

    private suspend fun insertListBeers(items: List<Beers>,beersDao: BeersDao){
        for (item in items)
            beersDao.inserBeer(item)
    }

}