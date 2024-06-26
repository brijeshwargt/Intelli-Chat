package com.dedsec.intellichat.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.dedsec.intellichat.navigation.Home

open class Event<out T>(val content: T){
    var hasBeenHandled = false
    fun getContentOrNull(): T? {
        return if (hasBeenHandled) null
        else {
            hasBeenHandled = false
            content
        }
    }
}

@Composable
fun ProgressionBar(){
    Row (
        modifier = Modifier
            .alpha(0.5f)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        CircularProgressIndicator(color = Color.LightGray)
    }
}

@Composable
fun CheckSignedIn(vm: viewModel, navHostController: NavHostController){
    val alreadySignedIn = remember {
        mutableStateOf(false)
    }

    val signIn = vm.signIn.value
    Log.i("Event", "Running CheckSignedIn with alreadySignedIn: $alreadySignedIn, signIn: $signIn")
    if (signIn && !alreadySignedIn.value){
        alreadySignedIn.value = true
        navHostController.navigate(Home){
            popUpTo(0){
                inclusive = true
            }
        }
    }
}
