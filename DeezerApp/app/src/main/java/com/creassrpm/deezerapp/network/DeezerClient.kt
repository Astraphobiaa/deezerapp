package com.creassrpm.deezerapp.network

import com.squareup.picasso.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DeezerClient {

    fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor (createHttpLoggingInterceptor(BuildConfig.DEBUG))
            .build()

    }

    private fun createHttpLoggingInterceptor(debugMode: Boolean): Interceptor {

        val httpLoginInterceptor = HttpLoggingInterceptor()
        if(!debugMode) httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoginInterceptor
    }

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(AppConst.deezerUrl)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }

}