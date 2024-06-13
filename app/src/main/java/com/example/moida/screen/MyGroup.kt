package com.example.moida.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.moida.R
import com.example.moida.model.Meeting
import com.google.firebase.database.FirebaseDatabase

@Composable
fun MyGroup() {
    val database = FirebaseDatabase.getInstance().reference
    var showCreateDialog: Boolean by remember { mutableStateOf(false) }
    var showJoinDialog by remember { mutableStateOf(false) }
    var meetings by remember { mutableStateOf(listOf<Meeting>()) }
    var isFabMenuExpanded by remember { mutableStateOf(false) }

    MyMeetingsScreen(
        meetings = meetings,
        isFabMenuExpanded = isFabMenuExpanded,
        onFabMenuToggle = { isFabMenuExpanded = !isFabMenuExpanded },
        onCreateMeeting = { showCreateDialog = true },
        onJoinMeeting = { showJoinDialog = true }
    )

    if (showCreateDialog) {
        CreateMeetingScreen(
            onDismiss = { showCreateDialog = false },
            onCreate = { groupName ->
                val uniqueCode = generateUniqueCode()
                val randomImageRes = getRandomImageRes()
                val group = Meeting(name = groupName, imageRes = randomImageRes, code = uniqueCode)
                database.child("groups").push().setValue(group).addOnCompleteListener {
                    if (it.isSuccessful) {
                        meetings = meetings + group
                    }
                    showCreateDialog = false
                }
            }
        )
    }

    if (showJoinDialog) {
        JoinMeetingScreen(
            onDismiss = { showJoinDialog = false },
            onJoin = { inviteCode ->
                database.child("groups").orderByChild("code").equalTo(inviteCode)
                    .get().addOnSuccessListener {
                        if (it.exists()) {
                            val group = it.children.first().getValue(Meeting::class.java)
                            group?.let { meeting ->
                                meetings = meetings + meeting
                            }
                        } else {
                            // 유효하지 않은 초대코드 예외처리
                        }
                        showJoinDialog = false
                    }
            }
        )
    }
}

fun generateUniqueCode(): String {
    return (100000..999999).random().toString()
}

fun getRandomImageRes(): Int {
    val images = listOf(
        R.drawable.sample_image1,
        R.drawable.sample_image2,
        R.drawable.sample_image3,
        R.drawable.sample_image4,
        R.drawable.sample_image5,
        R.drawable.sample_image6,
        R.drawable.sample_image7,
        R.drawable.sample_image8,
        R.drawable.sample_image9,
        R.drawable.sample_image10
    )
    return images.random()
}