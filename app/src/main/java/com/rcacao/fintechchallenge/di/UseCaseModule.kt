package com.rcacao.fintechchallenge.di

import com.rcacao.fintechchallenge.domain.usecase.GetContactsUseCase
import com.rcacao.fintechchallenge.domain.usecase.GetContactsUseCaseImpl
import com.rcacao.fintechchallenge.domain.usecase.SearchContactsUseCase
import com.rcacao.fintechchallenge.domain.usecase.SearchContactsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetContactsUseCase(getContactsUseCase: GetContactsUseCaseImpl): GetContactsUseCase

    @Singleton
    @Binds
    abstract fun bindSearchContactsUseCase(searchContactsUseCase: SearchContactsUseCaseImpl): SearchContactsUseCase

}
