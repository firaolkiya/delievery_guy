package com.example.taskapp.presentation.auth

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskapp.R
import com.example.taskapp.presentation.common.InputField
import com.example.taskapp.presentation.common.MyTextButton
import com.example.taskapp.presentation.common.NormalButton

@SuppressLint("ShowToast")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController:NavHostController,authViewmodel:AuthViewmodel){
    val authState = authViewmodel.authState.observeAsState()
    var password by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    val context = LocalContext.current
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated ->navController.navigate("client_dashboard")
            is AuthState.Error ->Toast.makeText(context,
                (authState.value as AuthState.Error).message,Toast.LENGTH_LONG).show()
            else ->Unit
        }

    }
    Scaffold (
      modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight()

    ){
        paddingValues ->
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(230, 230, 230, 255))
            .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column {

                    Image(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .height(250.dp)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.login),
                        contentDescription = "login"
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    InputField(hint = "Email", isPassword = false) {
                        email = it
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    InputField(hint = "Password", isPassword = true) {
                        password = it
                    }


                    Spacer(modifier = Modifier.height(8.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Forgot Password")
                        MyTextButton(text = "Recover Password") {
                        }

                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    NormalButton(text = "Login") {
                        authViewmodel.login(email, password)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Haven't you an Account?")
                        MyTextButton(text = "Create new") {
                            navController.navigate("signup")
                        }
                    }
                    if(authState.value is AuthState.Loading){
                        CircularProgressIndicator()
                    }
                }
            }

        }









        }
    }
