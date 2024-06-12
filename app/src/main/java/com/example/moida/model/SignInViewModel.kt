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

    fun initialize(context: Context) {
        prefsHelper = SharedPreferencesHelper(context) // SharedPreferencesHelper 초기화 추가
        val savedEmail = prefsHelper.getEmail()
        val savedPassword = prefsHelper.getPassword()
        val savedUsername = prefsHelper.getUsername()

        if (savedUsername != null) {
            _userName.value = savedUsername
        }

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
                        val username = document.getString("name")
                        if (username != null) {
                            prefsHelper.saveUsername(username)
                            _userName.value = username // 유저네임 업데이트
                        }
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

    suspend fun signOut() {
        auth.signOut()
        _userName.value = null
        _id.value = ""
        _password.value = ""
        prefsHelper.clearLoginDetails() // 자동 로그인 정보 제거
    }
}
