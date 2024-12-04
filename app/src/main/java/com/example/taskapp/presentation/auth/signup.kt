package com.example.taskapp.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskapp.R
import com.example.taskapp.domain.entity.UserEntity
import com.example.taskapp.presentation.common.InputField
import com.example.taskapp.presentation.common.MyTextButton
import com.example.taskapp.presentation.common.NormalButton
import com.example.taskapp.presentation.common.myBackground

@Composable
fun SignupPage(nacController:NavHostController,authViewmodel:AuthViewmodel){
    val authState = authViewmodel.authState.observeAsState()
    var password by remember{ mutableStateOf("") }
    var cpassword by remember{ mutableStateOf("") }

    var fullName by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }

    Scaffold {innerPAdding->
        LazyColumn (
            modifier = Modifier
                .padding(innerPAdding)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(myBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
           item {
               Column {


               Image(
                   painter = painterResource(id = R.drawable.delevry), contentDescription = "login",
                   modifier = Modifier
                       .padding(top = 30.dp)
                       .height(250.dp)
                       .fillMaxWidth()
               )

                Spacer(modifier = Modifier
                    .height(20.dp),)


                InputField(hint = "Full name", isPassword = false ) {value->
                    fullName = value
                }


                Spacer(modifier = Modifier
                    .height(15.dp),)


                InputField(hint = "Email Address", isPassword = false ) {value->
                    email = value

                }

                Spacer(modifier = Modifier
                    .height(15.dp),)

                InputField(hint = "Create password", isPassword = true ) {value->
                    password = value

                }


                Spacer(modifier = Modifier
                    .height(15.dp),)

                InputField(hint = "Conform password", isPassword = true ) {
                    cpassword = it
                }


                Spacer(modifier = Modifier.height(8.dp))


                NormalButton(text = "Signup") {
                    val user = UserEntity(
                        name = fullName,
                        role = "delivery_guy",
                        email = email,
                    )
                    authViewmodel.signUp(userEntity = user,password,cpassword)
                }

                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Haven you an Account?")
                    MyTextButton(text = "Login") {

                    }

            }
               }
           }



        }

    }
}


