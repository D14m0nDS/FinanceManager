package com.antoan.financemanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoan.financemanager.domain.repository.SettingsRepository
import com.antoan.financemanager.domain.model.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val theme: StateFlow<Theme> =
        settingsRepository.theme.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Theme.SYSTEM
        )

    fun setTheme(theme: Theme) {
        viewModelScope.launch {
            settingsRepository.setTheme(theme)
        }
    }

    fun toggleDarkLight() {
        val next = when (theme.value) {
            Theme.DARK -> Theme.LIGHT
            Theme.LIGHT -> Theme.DARK
            Theme.SYSTEM -> Theme.DARK
        }
        setTheme(next)
    }
}
