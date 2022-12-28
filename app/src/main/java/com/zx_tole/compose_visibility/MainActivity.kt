package com.zx_tole.compose_visibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zx_tole.compose_visibility.ui.theme.Compose_visibilityTheme

private val visibility = mutableStateOf(false)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_visibilityTheme {
                Row {
                    Surface(
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                    ) {
                        CreateButton("Android") {
                            visibility.value = !visibility.value
                        }
                    }

                    Column {
                        HelloAnim("test visibility animation")
                    }
                }
            }
        }
    }
}

@Composable
fun CreateButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
    ) {
        Text(text = text, color = Color.White)
    }
}

@Composable
fun HelloAnim(name: String) {
    AnimatedVisibility(
        visible = visibility.value,
        enter = fadeIn(animationSpec = tween(2000)),
        exit = fadeOut(animationSpec = tween(2000))
    ) {
        Text(text = "Hello $name!")
    }
}