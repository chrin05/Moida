package com.example.moida.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuthException

object AuthUtils {

    val errorMessages = mapOf(
        "ERROR_INVALID_CUSTOM_TOKEN" to "유효하지 않은 커스텀 토큰입니다.",
        "ERROR_CUSTOM_TOKEN_MISMATCH" to "커스텀 토큰이 일치하지 않습니다.",
        "ERROR_INVALID_CREDENTIAL" to "유효하지 않은 자격 증명입니다.",
        "ERROR_INVALID_EMAIL" to "유효하지 않은 이메일 형식입니다.",
        "ERROR_WRONG_PASSWORD" to "잘못된 비밀번호입니다.",
        "ERROR_USER_MISMATCH" to "사용자 정보가 일치하지 않습니다.",
        "ERROR_REQUIRES_RECENT_LOGIN" to "최근에 다시 로그인해야 합니다.",
        "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" to "다른 자격 증명으로 이미 존재하는 계정입니다.",
        "ERROR_EMAIL_ALREADY_IN_USE" to "이미 사용 중인 이메일입니다.",
        "ERROR_CREDENTIAL_ALREADY_IN_USE" to "자격 증명이 이미 사용 중입니다.",
        "ERROR_USER_DISABLED" to "사용자 계정이 비활성화되었습니다.",
        "ERROR_USER_TOKEN_EXPIRED" to "사용자 토큰이 만료되었습니다.",
        "ERROR_USER_NOT_FOUND" to "사용자를 찾을 수 없습니다.",
        "ERROR_INVALID_USER_TOKEN" to "유효하지 않은 사용자 토큰입니다.",
        "ERROR_OPERATION_NOT_ALLOWED" to "허용되지 않은 작업입니다.",
        "ERROR_WEAK_PASSWORD" to "비밀번호가 너무 약합니다."
    )

    fun getErrorMessage(exception: Exception?): String {
        return if (exception is FirebaseAuthException) {
            errorMessages[exception.errorCode] ?: "알 수 없는 오류가 발생했습니다."
        } else {
            "알 수 없는 오류가 발생했습니다."
        }
    }

    val errorMessage: MutableState<String?> = mutableStateOf(null)
}