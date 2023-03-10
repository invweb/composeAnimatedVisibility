package com.zx_tole.compose_visibility

import android.os.Bundle
import android.widget.Toast
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private val visibility = mutableStateOf(false)

class MainActivity : ComponentActivity() {
    private val ctx = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_visibilityTheme {
                Column {
                    Row {
                        Surface(
                            modifier = Modifier
                                .padding(8.dp)
                                .width(170.dp)
                                .height(50.dp)
                        ) {
                            CreateButton("view AnimatedVisibility") {
                                visibility.value = !visibility.value
                            }
                        }

                        Column {
                            HelloAnim("test visibility animation")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Surface{
                            CreateButton("AlertDialog") {
                                visibility.value = !visibility.value
                                setContent {
                                    val openDialog = remember { mutableStateOf(true) }
                                    if (openDialog.value) AlertDialog(
                                        title = { Text(text = "?????????????????????????? ????????????????") },
                                        text = { Text("???? ?????????????????????????? ????????????" +
                                                " ?????????????????? ?????????????????") },
                                        onDismissRequest = {
                                        },
                                        confirmButton = {
                                            TextButton(onClick = {
                                                Toast.makeText(
                                                    ctx,
                                                    "test",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            })
                                            { Text(text = "OK") }
                                        },
                                        dismissButton = {
                                            TextButton(onClick = {
                                                openDialog.value = false
                                            })
                                            { Text(text = "Cancel") }
                                        }
                                    )
                                }
                            }
                        }
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