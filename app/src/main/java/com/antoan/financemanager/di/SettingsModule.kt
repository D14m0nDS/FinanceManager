package com.antoan.financemanager.di

import android.content.Context
import com.antoan.financemanager.data.preferences.AppPreferences
import com.antoan.financemanager.data.repository.SettingsRepositoryImpl
import com.antoan.financemanager.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences =
        AppPreferences(context)

    @Provides
    @Singleton
    fun provideSettingsRepository(prefs: AppPreferences): SettingsRepository =
        SettingsRepositoryImpl(prefs)
}
