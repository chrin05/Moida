package com.example.moida.model

import com.example.moida.R

data class BottomNavigationItem(
    val label : Int = R.string.home,
    val icon : Int = R.drawable.home,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = R.string.home,
                icon = R.drawable.home,
                route = BottomNavItem.Home.route
            ),
            BottomNavigationItem(
                label = R.string.group,
                icon = R.drawable.group,
                route = BottomNavItem.Group.route
            ),
            BottomNavigationItem(
                label = R.string.my,
                icon = R.drawable.my,
                route = BottomNavItem.My.route
            ),
        )
    }
}
