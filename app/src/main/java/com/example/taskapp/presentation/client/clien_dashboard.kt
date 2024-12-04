package com.example.taskapp.presentation.client

import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.taskapp.R
import com.example.taskapp.presentation.auth.AuthViewmodel
import com.example.taskapp.presentation.common.InputField
import com.example.taskapp.presentation.common.MyTextButton
import com.example.taskapp.presentation.common.myBackground
import com.example.taskapp.presentation.common.myBlueColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientDashboard(navController: NavHostController,authViewmodel: AuthViewmodel){
    Scaffold(
        modifier = Modifier.background(myBackground),
        topBar = {
            TopAppBar(

               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = myBlueColor,
                   titleContentColor = Color.White
               ),
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "profile",
                        modifier = Modifier
                            .size(50.dp) // Ensures width and height are equal
                            .clip(CircleShape),// Clip to a circular shape

                        contentScale = ContentScale.FillBounds, // Fills the circle
                    )

                },
                title = { Text(text = "Welcome Back!",
                    style = MaterialTheme.typography.headlineMedium)}
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .background(myBackground)
                .padding(paddingValues)
                .padding(15.dp)
                .fillMaxWidth()
                .fillMaxHeight()

        ) {

            item {
                Column {
                    Spacer(modifier = Modifier.height(20.dp))
                    InputField(hint = "Search", isPassword = false) {
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                }
            }

            items(20) { index ->
                Spacer(modifier = Modifier.height(10.dp))
                ListTile(
                    onClick = {
                        navController.navigate("map_page")
                    }
                ) // Pass item to your ListTile
            }

        }
    }
}

@Composable
fun ListTile(
     onClick:()->Unit
){
        Row(

            modifier = Modifier
                .indication(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null // Ripple effect color
                )
                .clickable(
                    onClick = {
                        onClick()
                    },
                )
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(15.dp))
                .background(Color.White)

                .padding(horizontal = 12.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically

        ){
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",

                modifier = Modifier
                    .size(60.dp) // Ensures width and height are equal
                    .clip(CircleShape),// Clip to a circular shape

                contentScale = ContentScale.FillBounds, // Fills the circle
            )
            Column (
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Yonathan Caligure", style = MaterialTheme.typography.headlineMedium.copy(
                    Color(24, 31, 37, 255),
                    fontSize = 22.sp
                ))
                TextButton(

                    onClick = { /*TODO*/ }) {
                    Text(text = "See Location", style = TextStyle(
                        fontSize = 18.sp,
                        color = Color(2, 13, 75, 255)
                    ))
                }
            }
        }

}

