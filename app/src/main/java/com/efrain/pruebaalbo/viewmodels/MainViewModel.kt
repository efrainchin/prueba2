package com.efrain.pruebaalbo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Created by Carlos chin on 31/12/2020.
 * Correo: efrain@sunwise.io
 */
class MainViewModel : ViewModel() {
    private val liveDataTitle = MutableLiveData<String>()

    fun setTile(title: String) {
        liveDataTitle.value = title
    }

    fun getObserveTitle() = liveDataTitle
}