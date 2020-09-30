package com.rcacao.fintechchallenge.data.api

import com.rcacao.fintechchallenge.data.model.Contact
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {

    @GET("/contacts")
    suspend fun contacts(): List<Contact>

    companion object {
        private const val BASE_URL = "http://demo4018467.mockable.io/"

        fun create(): WebService {
            val logger: HttpLoggingInterceptor =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }
    }
}