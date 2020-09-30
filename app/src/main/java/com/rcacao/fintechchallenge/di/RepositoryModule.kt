package com.rcacao.fintechchallenge.di

import com.rcacao.fintechchallenge.data.datasource.RemoteDataSource
import com.rcacao.fintechchallenge.data.datasource.RemoteDataSourceImpl
import com.rcacao.fintechchallenge.data.repository.ContactsRepository
import com.rcacao.fintechchallenge.data.repository.ContactsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindContactsRepository(contactsRepository: ContactsRepositoryImpl): ContactsRepository

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

}
