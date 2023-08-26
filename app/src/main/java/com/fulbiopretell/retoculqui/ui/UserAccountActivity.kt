@file:OptIn(ExperimentalMaterial3Api::class)

package com.fulbiopretell.retoculqui.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fulbiopretell.retoculqui.ui.components.LoginScreen
import com.fulbiopretell.retoculqui.ui.components.RegisterScreen
import com.fulbiopretell.retoculqui.ui.components.WelcomeScreen
import com.fulbiopretell.retoculqui.ui.theme.RetoCulquiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserAccountActivity : ComponentActivity() {

    private lateinit var navController: NavController
    private val viewModel: UserAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetoCulquiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    NavHost(navController as NavHostController, startDestination = "welcome") {
                        composable("welcome") { WelcomeScreen(viewModel, navController) }
                        composable("login") { LoginScreen(viewModel, navController) }
                        composable("register") { RegisterScreen(viewModel, navController) }
                    }
                }
            }
        }

        viewModel.errorUserSaved.observe(this, { errorMessage ->
            navController.navigate("register")
        })

        viewModel.userSaved.observe(this, { user ->
            navController.navigate("login")
        })

        viewModel.userLogged.observe(this, { isSuccess ->
            if (isSuccess) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_LONG).show()
            }
        })
    }
}


