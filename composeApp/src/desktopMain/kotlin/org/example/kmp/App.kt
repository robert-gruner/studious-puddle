package org.example.kmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.kmp.pets.PetDetail
import org.example.kmp.pets.PetList
import org.jetbrains.compose.ui.tooling.preview.Preview

sealed class AppScreen(val route: String) {
    data object PetList : AppScreen("petList")
    data object PetDetail : AppScreen("petDetail/{id}") {
        fun createRoute(id: Int) = "petDetail/$id"
    }
}

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    MaterialTheme {
        Scaffold {
            NavHost(
                navController = navController,
                startDestination = AppScreen.PetList.route,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                composable(route = AppScreen.PetList.route) {
                    PetList(
                        onItemClick = {
                            navController.navigate(
                                route = AppScreen.PetDetail.createRoute(it),
                            )
                        }
                    )
                }
                composable(route = AppScreen.PetDetail.route, arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("id")

                    id?.let {
                        PetDetail(id = id,  onBackClick = {
                            navController.popBackStack()
                        })
                    }
                }
            }

        }
    }
}