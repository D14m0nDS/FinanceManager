package com.antoan.financemanager.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.antoan.financemanager.domain.model.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "app_prefs")
class AppPreferences(private val context: Context) {

    private val THEME = stringPreferencesKey("theme")

    val theme: Flow<Theme> =
        context.dataStore.data.map { preferences ->
            val raw = preferences[THEME] ?: Theme.SYSTEM.name
            runCatching { Theme.valueOf(raw) }.getOrDefault(Theme.SYSTEM)
        }

    suspend fun setTheme(theme: Theme) {
        context.dataStore.edit { preferences ->
            preferences[THEME] = theme.name
        }
    }

}