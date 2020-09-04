package com.programmergabut.mvvm_generator_by_jiwo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.programmergabut.mvvm_generator_by_jiwo.Constants
import com.programmergabut.mvvm_generator_by_jiwo.data.remote.api.SomeService
import com.programmergabut.mvvm_generator_by_jiwo.data.remote.remoteentity.SomeResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun someEndPoint() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideSomeService(retrofit: Retrofit): SomeService = retrofit.create(SomeService::class.java)


}