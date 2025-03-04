package org.example.kmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val petsApi = PetsApiClient()

    MaterialTheme {
        val scope = rememberCoroutineScope()
        var showContent by remember { mutableStateOf(false) }
        var text by remember { mutableStateOf("Loading...") }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            LaunchedEffect(true) {
                scope.launch {
                    text = try {
                        petsApi.fetchPets().getOrElse(0) {
                            Pet(
                                name = "Diego",
                                photoUrls = listOf("https://images.dog.ceo/breeds/setter-irish/n02100877_4371.jpg"),
                                id = null,
                                category = null,
                                status = null,
                            )
                        }.name
                    } catch (e: Exception) {
                        e.localizedMessage ?: "error"
                    }
                }
            }
            Button(onClick = { showContent = !showContent }) {
                Text("Fetch pets!")
            }
            AnimatedVisibility(showContent) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text)
                }
            }
        }
    }
}