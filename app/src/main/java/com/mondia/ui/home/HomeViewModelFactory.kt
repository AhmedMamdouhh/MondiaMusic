package com.mondia.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mondia.manager.utilities.SharedPreferencesManager
import com.mondia.repository.HomeRepository

class HomeViewModelFactory(
    private val sharedPreferences: SharedPreferencesManager,
    private val homeRepository: HomeRepository,
    private val token: String
) : ViewModelProvider.Factory {

    // 2
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(sharedPreferences, homeRepository,token) as T
    }
}