package com.example.moida.screen

import android.util.Log
import android.widget.ImageView
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
    var showJoinError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
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
        onJoinMeeting = {
            showJoinDialog = true
            showJoinError = false // Reset error state when opening dialog
        }
    )

    if (showJoinDialog || showJoinError) {
        JoinMeetingScreen(
            onDismiss = {
                showJoinDialog = false
                showJoinError = false
            },
            onJoin = { inviteCode ->
                database.child("groups").orderByChild("code").equalTo(inviteCode)
                    .get().addOnSuccessListener {
                        if (it.exists()) {
                            val group = it.children.first().getValue(Meeting::class.java)
                            group?.let { meeting ->
                                val userEmail = currentUser?.email
                                if (userEmail != null && !meeting.members.any { it["memberEmail"] == userEmail }) {
                                    val updatedMeeting = meeting.copy(
                                        members = meeting.members + mapOf("memberEmail" to userEmail)
                                    )
                                    val meetingKey = it.children.first().key
                                    if (meetingKey != null) {
                                        database.child("groups").child(meetingKey).setValue(updatedMeeting)
                                    }
                                }
                                meetings = meetings + meeting
                                showJoinDialog = false
                                showJoinError = false
                            }
                        } else {
                            errorMessage = "유효하지 않은 초대코드입니다."
                            showJoinError = true
                        }
                    }.addOnFailureListener {
                        Log.e("MyGroup", "Error getting data", it)
                    }
            },
            errorMessage = errorMessage,
            showError = showJoinError
        )
    }
}

fun generateUniqueCode(): String {
    return (100000..999999).random().toString()
}
