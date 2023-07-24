package com.pablichj.incubator.uistate3.example.helloWorld

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun HelloWorldView(helloWorldState: HelloWorldState) {
    val userName = helloWorldState.userName

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.fillMaxWidth().height(48.dp))
        Text("Enter your name:")
        OutlinedTextField(
            value = userName,
            onValueChange = {
                helloWorldState.userName = it
            }
        )
        Text("Great to meet you: $userName")
    }
    
}