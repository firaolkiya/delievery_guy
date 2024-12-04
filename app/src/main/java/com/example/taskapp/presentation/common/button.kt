package com.example.taskapp.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NormalButton(text:String,onclick: ()->Unit){
    return Button(
        modifier = Modifier

            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .clip(shape = RoundedCornerShape(20.dp))

            .border(
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Gray
                )
            )
            .background(Color(3, 169, 244, 255)),
        colors = ButtonColors(
            containerColor = Color(3, 169, 244, 255),
            contentColor = Color.White,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color(3, 169, 244, 255)
        ),
        onClick = { onclick() }) {
        Text(text = text)

    }
}

@Composable
fun MyTextButton(text:String,onclick:()->Unit){
    return  TextButton(
        content = { Text(text = text) },
        onClick = onclick)
}

