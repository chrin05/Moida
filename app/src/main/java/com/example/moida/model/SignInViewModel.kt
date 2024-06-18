package com.example.moida.screen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.moida.util.SharedPreferencesHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInViewModel(private val prefsHelper: SharedPreferencesHelper) : ViewModel() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { Firebase.firestore }

    private val _id = MutableStateFlow("")
    val id: StateFlow<String> get() = _id

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> get() = _userName

    init {
        initialize()
    }

    private fun initialize() {
        val savedEmail = prefsHelper.getEmail()
        val savedPassword = prefsHelper.getPassword()
        val savedUsername = prefsHelper.getUsername()

        if (savedUsername != null) {
            _userName.value = savedUsername
        }

        if (savedEmail != null && savedPassword != null) {
            signIn(savedEmail, savedPassword)
        }
    }

    fun onIdChange(newId: String) {
        _id.value = newId
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun signIn(email: String = _id.value, password: String = _password.value) {
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

    suspend fun updateUserName(newName: String): Boolean {
        val user = auth.currentUser
        return user?.let {
            try {
                db.collection("users").document(user.uid)
                    .update("name", newName).await()
                prefsHelper.saveUsername(newName)
                _userName.value = newName
                true
            } catch (e: Exception) {
                Log.e("UpdateUser", "Failed to update user name", e)
                false
            }
        } ?: false
    }

    suspend fun signOut() {
        auth.signOut()
        _userName.value = null
        _id.value = ""
        _password.value = ""
        prefsHelper.clearLoginDetails() // 자동 로그인 정보 제거
    }

    fun deleteUser() {
        val user = auth.currentUser
        user?.let {
            // Firestore 사용자 데이터 삭제
            db.collection("users").document(user.uid)
                .delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Firebase Authentication 사용자 삭제
                        it.delete()
                            .addOnCompleteListener { deleteTask ->
                                if (deleteTask.isSuccessful) {
                                    // 회원 탈퇴 성공
                                    prefsHelper.clearLoginDetails()
                                    _userName.value = null
                                    _id.value = ""
                                    _password.value = ""
                                    Log.d("DeleteUser", "User account deleted.")
                                } else {
                                    // 회원 탈퇴 실패
                                    Log.e("DeleteUser", "User account deletion failed.", deleteTask.exception)
                                }
                            }
                    } else {
                        // Firestore 사용자 데이터 삭제 실패
                        Log.e("DeleteUser", "User data deletion failed.", task.exception)
                    }
                }
        }
    }

}



class SignInViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            val prefsHelper = SharedPreferencesHelper(context)
            return SignInViewModel(prefsHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


