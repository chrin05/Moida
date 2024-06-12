package com.example.moida.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class JoinMembershipViewModel() : ViewModel() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { Firebase.firestore }

    private val _id = MutableStateFlow("")
    val id: StateFlow<String> get() = _id

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> get() = _name

    private val _signUpSuccess = MutableStateFlow(false)
    val signUpSuccess: StateFlow<Boolean> get() = _signUpSuccess

    fun onIdChange(newId: String) {
        _id.value = newId
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun signUp() {
        if (_id.value.isNotEmpty() && _password.value.isNotEmpty() && _name.value.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val result = auth.createUserWithEmailAndPassword(_id.value, _password.value).await()
                    val user = result.user
                    if (user != null) {
                        db.collection("users").document(user.uid).set(mapOf("name" to _name.value)).await()
                        AuthUtils.setErrorMessage("회원 가입에 성공했습니다.")
                        _signUpSuccess.value = true
                    } else {
                        AuthUtils.setErrorMessage("회원 가입에 실패했습니다.")
                    }
                } catch (e: Exception) {
                    AuthUtils.setErrorMessage(AuthUtils.getErrorMessage(e))
                }
            }
        } else {
            AuthUtils.setErrorMessage("모든 필드를 입력해주세요")
        }
    }
}
