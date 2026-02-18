package com.antoan.financemanager.data.repository

import com.antoan.financemanager.data.preferences.AppPreferences
import com.antoan.financemanager.domain.repository.SettingsRepository
import com.antoan.financemanager.domain.model.Theme
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val prefs: AppPreferences
) : SettingsRepository {
    override val theme: Flow<Theme> = prefs.theme
    override suspend fun setTheme(theme: Theme) = prefs.setTheme(theme)
}