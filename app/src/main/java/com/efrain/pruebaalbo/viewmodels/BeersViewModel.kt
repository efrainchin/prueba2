package com.efrain.pruebaalbo.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efrain.pruebaalbo.data.PruebAlboDatabase
import com.efrain.pruebaalbo.model.Beers
import com.efrain.pruebaalbo.model.DetailBeers
import com.efrain.pruebaalbo.repository.BeersRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch


/**
 * Created by Carlos chin on 31/12/2020.
 * Correo: efrain@sunwise.io
 */
class BeersViewModel @ViewModelInject constructor(private val beersRepository: BeersRepository) : ViewModel() {

    private var liveDataBeers = MutableLiveData<List<Beers>>()
    private var liveDataDetailBeer = MutableLiveData<List<DetailBeers>>()

    fun getBeers(page: Int, per_page: Int) = viewModelScope.launch {
            liveDataBeers.value = beersRepository.getBeers(page, per_page)
        }

    fun getBeerById(id: String) = viewModelScope.launch {
            liveDataDetailBeer.value = beersRepository.getBeerById(id)
        }

    fun getObserveLiveDataBeers() = liveDataBeers
    fun getObserveLiveDetailBeer() = liveDataDetailBeer
}