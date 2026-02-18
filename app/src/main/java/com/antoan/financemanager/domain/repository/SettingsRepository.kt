package com.antoan.financemanager.domain.repository

import com.antoan.financemanager.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val theme: Flow<Theme>
    suspend fun setTheme(theme: Theme)
}