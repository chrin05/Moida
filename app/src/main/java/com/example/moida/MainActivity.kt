package com.example.moida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.moida.screen.GroupDetail
import com.example.moida.screen.JoinMembership
import com.example.moida.screen.MainHome
import com.example.moida.screen.SignIn
import com.example.moida.ui.theme.MoidaTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            MoidaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // SignIn()
                    JoinMembership()
                    //MainHome()
                    //GroupDetail()
                }
            }
        }
    }
}
