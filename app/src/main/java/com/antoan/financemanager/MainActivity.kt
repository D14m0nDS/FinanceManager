package com.antoan.financemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antoan.financemanager.domain.model.Theme
import com.antoan.financemanager.ui.theme.FinanceManagerTheme
import com.antoan.financemanager.ui.viewmodel.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.antoan.financemanager.ui.navigation.AppNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm: ThemeViewModel = hiltViewModel()
            val theme by vm.theme.collectAsStateWithLifecycle()

            val useDarkTheme = when (theme) {
                Theme.DARK -> true
                Theme.LIGHT -> false
                Theme.SYSTEM -> isSystemInDarkTheme()
            }

            FinanceManagerTheme(darkTheme = useDarkTheme, dynamicColor = false) {
                AppNavigation(
                    onToggleTheme = { vm.toggleDarkLight() },
                    onSetTheme = { vm.setTheme(it) },
                    theme = theme
                )
            }
        }
    }
}