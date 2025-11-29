package com.example.electraconnect

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object ValidatorModule {

    @Provides
    @Singleton
    fun provideValidator(): ValidationUtils {
        return ValidationUtils()
    }

}