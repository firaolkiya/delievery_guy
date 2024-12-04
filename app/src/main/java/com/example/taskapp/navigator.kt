package com.example.taskapp

import androidx.compose.runtime.Composable
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskapp.data.repositary_impl.AuthRepositoryImpl
import com.example.taskapp.presentation.auth.AuthViewmodel
import com.example.taskapp.presentation.auth.LoginPage
import com.example.taskapp.presentation.auth.SignupPage
import com.example.taskapp.presentation.client.ClientDashboard
import com.example.taskapp.presentation.client.TrackLocation
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Navigator(){
    val navController =  rememberNavController()
    val firestore = FirebaseFirestore.getInstance()
    val authRepository = AuthRepositoryImpl(firestore)
    val authViewmodel = AuthViewmodel(authRepository)
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginPage(navController,authViewmodel) }
        composable("client_dashboard") { ClientDashboard(navController,authViewmodel) }
        composable("signup") { SignupPage(navController,authViewmodel) }
        composable("map_page") { TrackLocation(navController) }
    }
}