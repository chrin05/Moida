package com.example.moida.util

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moida.screen.SignInViewModel

class SharedPreferencesHelper(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("moida_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }

    fun saveLoginDetails(email: String, password: String) {
        prefs.edit().apply {
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD, password)
            apply()
        }
    }

    fun getEmail(): String? {
        return prefs.getString(KEY_EMAIL, null)
    }

    fun getPassword(): String? {
        return prefs.getString(KEY_PASSWORD, null)
    }

    fun clearLoginDetails() {
        prefs.edit().apply {
            remove(KEY_EMAIL)
            remove(KEY_PASSWORD)
            apply()
        }
    }
}

class SignInViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignInViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}