//package com.example.moida.model
//
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
//data class CustomAlertDialogState(
//    val title: String = "",
//    val description: String = "",
//    val onClickConfirm: () -> Unit = {},
//    val onClickCancel: () -> Unit = {},
//)
//
//// MainViewModel.kt
//@HiltViewModel
//class DialogViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle?,
//) : ViewModel() {
//    val customAlertDialogState: MutableState<CustomAlertDialogState> = mutableStateOf<CustomAlertDialogState>(
//        CustomAlertDialogState()
//    )
//    fun showCustomAlertDialog() {
//        customAlertDialogState.value = CustomAlertDialogState(
//            title = "정말로 삭제하시겠습니까?",
//            description = "삭제하면 복구할 수 없습니다.",
//            onClickConfirm = {
//                resetDialogState()
//            },
//            onClickCancel = {
//                resetDialogState()
//            }
//        )
//    }
//    // 다이얼로그 상태 초기화
//    private fun resetDialogState() {
//        customAlertDialogState.value = CustomAlertDialogState()
//    }
//}