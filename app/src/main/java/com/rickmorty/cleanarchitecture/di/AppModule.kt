package com.rickmorty.cleanarchitecture.di

import com.rickmorty.cleanarchitecture.common.Constants.BASE_URL
import com.rickmorty.cleanarchitecture.data.remote.CharacterPaprikaApi
import com.rickmorty.cleanarchitecture.data.repository.CharacterRepositoryImpl
import com.rickmorty.cleanarchitecture.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CharacterPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: CharacterPaprikaApi): CharacterRepository{
        return CharacterRepositoryImpl(api)
    }
}