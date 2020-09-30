package com.rcacao.fintechchallenge.di

import com.rcacao.fintechchallenge.data.api.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesService(): WebService = WebService.create()

}
