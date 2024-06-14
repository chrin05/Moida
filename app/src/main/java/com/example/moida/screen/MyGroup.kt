package com.example.moida.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.moida.R
import com.example.moida.model.Meeting
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

@Composable
fun MyGroup(
    navController: NavHostController
) {
    val database = FirebaseDatabase.getInstance().reference
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    var showJoinDialog by remember { mutableStateOf(false) }
    var meetings by remember { mutableStateOf(listOf<Meeting>()) }
    var isFabMenuExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (currentUser != null) {
            val userEmail = currentUser.email
            val meetingsListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val meetingList = mutableListOf<Meeting>()
                    for (meetingSnapshot in dataSnapshot.children) {
                        val meeting = meetingSnapshot.getValue(Meeting::class.java)
                        if (meeting != null && meeting.members.any { it["memberEmail"] == userEmail }) {
                            meetingList.add(meeting)
                        }
                    }
                    meetings = meetingList
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("MyGroup", "Database error: ${databaseError.message}")
                }
            }
            database.child("groups").addValueEventListener(meetingsListener)
        }
    }

    MyMeetingsScreen(
        navController = navController,
        meetings = meetings,
        isFabMenuExpanded = isFabMenuExpanded,
        onFabMenuToggle = { isFabMenuExpanded = !isFabMenuExpanded },
        onJoinMeeting = { showJoinDialog = true }
    )

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