package com.example.moida.util

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moida.screen.SignInViewModel
import javax.inject.Inject

class SharedPreferencesHelper(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("moida_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_USERNAME = "username"
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

    fun saveUsername(username: String) {
        prefs.edit().apply {
            putString(KEY_USERNAME, username)
            apply()
        }
    }

    fun getUsername(): String? {
        return prefs.getString(KEY_USERNAME, null)
    }

    fun clearLoginDetails() {
        prefs.edit().apply {
            remove(KEY_EMAIL)
            remove(KEY_PASSWORD)
            remove(KEY_USERNAME)
            commit()
        }
    }
}