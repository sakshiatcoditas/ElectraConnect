package com.example.electraconnect.navigation



sealed class Screen(val route: String) {

    data object Login : Screen("login")
    data object SetNewPassword : Screen("set_new_password/{email}/{temp}") {
        fun pass(email: String, temp: String) = "set_new_password/$email/$temp"
    }
    data object Home : Screen("home")
}
