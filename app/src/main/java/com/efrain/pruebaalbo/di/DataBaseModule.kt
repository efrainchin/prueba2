package com.efrain.pruebaalbo.di

import android.content.Context
import com.efrain.pruebaalbo.data.PruebAlboDatabase
import com.efrain.pruebaalbo.data.dao.BeersDao
import com.efrain.pruebaalbo.model.Beers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


/**
 * Created by Carlos chin on 02/01/2021.
 * Correo: efrain@sunwise.io
 */

@InstallIn(ApplicationComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideGetAllBeers(@ApplicationContext context: Context): PruebAlboDatabase =
        PruebAlboDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideBeersDao(pruebAlboDatabase: PruebAlboDatabase): BeersDao = pruebAlboDatabase.getUserDao()
}