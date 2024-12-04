package com.example.taskapp.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputField(hint:String,
               isPassword:Boolean,
               onChange:(String)->Unit){
    var inputValue by remember {
        mutableStateOf("")
    }
    var isFocused by remember {
        mutableStateOf(false)
    }
      TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = 20.dp)
            .onFocusChanged { focusState -> // Listen for focus changes
                isFocused = focusState.isFocused
            }
            .clip(shape = RoundedCornerShape(12.dp))
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = if (isFocused) {
                        Color.Blue
                    }
                    else{
                        Color.Transparent
                    },


                    ),
                shape = RoundedCornerShape(12.dp)
            ),

        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Blue,

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),

        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        }else{
            VisualTransformation.None
        },
        singleLine = true, // Ensu
        placeholder = { Text(text = hint) } , // Use a lambda for the placeholder // re the text field is single-line
        value = inputValue,
        onValueChange = {value->
            inputValue = value
            onChange(value)
        },
// Simplified lambda without type
    )
}