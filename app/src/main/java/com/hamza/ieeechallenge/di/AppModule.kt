package com.hamza.ieeechallenge.di

import com.hamza.ieeechallenge.data.firebase.FirebaseSourceChat
import com.hamza.ieeechallenge.data.firebase.FirebaseSourceUser
import com.hamza.ieeechallenge.data.firebase.FirebaseSourceUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import android.app.Application
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideFirebaseSourceUser() = FirebaseSourceUser()

    @Singleton
    @Provides
    fun provideFirebaseSourceUtil() = FirebaseSourceUtil()

    @Singleton
    @Provides
    fun provideFirebaseSourceChat() = FirebaseSourceChat()

}