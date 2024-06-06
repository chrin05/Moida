package com.example.moida.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moida.R
import com.example.moida.screen.MainHome
import com.example.moida.screen.MyPage

sealed class BottomNavItem(val title: Int, val icon: Int, var route: String) {
    data object Home : BottomNavItem(R.string.home, R.drawable.home, "home")
    data object Group : BottomNavItem(R.string.group, R.drawable.group, "group")
    data object My : BottomNavItem(R.string.my, R.drawable.my, "my")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            MainHome()
        }
        composable(BottomNavItem.Group.route) {
//            MainGroup()
        }
        composable(BottomNavItem.My.route) {
            MyPage()
        }
    }
}