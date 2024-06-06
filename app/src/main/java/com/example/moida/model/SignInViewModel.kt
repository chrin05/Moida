package com.example.moida.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moida.util.SharedPreferencesHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInViewModel : ViewModel() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { Firebase.firestore }
    private lateinit var prefsHelper: SharedPreferencesHelper

    private val _id = MutableStateFlow("")
    val id: StateFlow<String> get() = _id

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> get() = _userName

    private val _lastLoggedOutEmail = MutableStateFlow<String?>(null)
    val lastLoggedOutEmail: StateFlow<String?> get() = _lastLoggedOutEmail

    fun initialize(context: Context) {
        prefsHelper = SharedPreferencesHelper(context)
        val savedEmail = prefsHelper.getEmail()
        val savedPassword = prefsHelper.getPassword()
        if (savedEmail != null && savedPassword != null) {
            signIn(savedEmail, savedPassword, context)
        }
    }

    fun onIdChange(newId: String) {
        _id.value = newId
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun signIn(email: String = _id.value, password: String = _password.value, context: Context) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val result = auth.signInWithEmailAndPassword(email, password).await()
                    val user = result.user
                    if (user != null) {
                        val document = db.collection("users").document(user.uid).get().await()
                        _userName.value = document.getString("name")
                        prefsHelper.saveLoginDetails(email, password)
                        AuthUtils.setErrorMessage(null)
                    } else {
                        AuthUtils.setErrorMessage("사용자 정보를 가져올 수 없습니다.")
                    }
                } catch (e: Exception) {
                    AuthUtils.setErrorMessage(AuthUtils.getErrorMessage(e))
                }
            }
        } else {
            AuthUtils.setErrorMessage("모든 필드를 입력해주세요")
        }
    }

    fun signOut() {
        val email = auth.currentUser?.email
        auth.signOut()
        _userName.value = null
        prefsHelper.clearLoginDetails()
        _lastLoggedOutEmail.value = email
    }
}
