package com.fulbiopretell.retoculqui.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fulbiopretell.retoculqui.R
import com.fulbiopretell.retoculqui.ui.UserAccountViewModel

@Composable
fun RegisterScreen(viewModel: UserAccountViewModel, navController: NavController) {

    val email = viewModel.email.value
    val name = viewModel.name.value
    val password = viewModel.password.value

    val backgroundImage: Painter = painterResource(id = R.drawable.background_image)
    val navigationIcon: Painter = painterResource(id = R.drawable.ic_navigation_arrow)
    val textState = remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        //Icono de navegación
        Column {
            IconButton(
                onClick = { navController.popBackStack() }, modifier = Modifier.padding(5.dp)
            ) {
                Icon(
                    painter = navigationIcon,
                    contentDescription = "Navegar atrás",
                    tint = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Sign up",
                textAlign = TextAlign.Start,
                style = TextStyle(fontSize = 30.sp),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )

            Box(Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 64.dp)
                        .blur(
                            radius = 10.dp,
                            edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(15.dp))
                        )
                        .clip(RoundedCornerShape(16.dp)) // Redondea las esquinas
                        .background(Color.White.copy(alpha = 0.2f)) // Fondo transparente
                        .graphicsLayer {
                            // Aquí puedes aplicar el efecto de desenfoque si es posible en tu versión de Compose
                        },
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.size(16.dp))

                    Text(
                        text = "Looks like you don't have an account. Let's create a new account for $email.",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        color = Color.White
                    )

                    RoundedEditText("Name", viewModel.name.value, onValueChange = {
                        // Actualización del estado cuando el valor del texto cambia
                        textState.value = it
                        viewModel.onNameChanged(it)
                    })

                    RoundedEditText("Password", viewModel.password.value, onValueChange = {
                        // Actualización del estado cuando el valor del texto cambia
                        textState.value = it
                    viewModel.onPasswordChanged(it)
                    })

                    val text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("By selecting Agree and continue bellow, I agreee to ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(id = R.color.green_culqui),
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Terms of Service and Privacy Policy")
                        }
                        append(".")
                    }

                    Text(
                        text = text,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                    )

                    Button(
                        onClick = { viewModel.registerUser(email, password) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 16.dp, vertical = 5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green_culqui)),
                        shape = RoundedCornerShape(5.dp)// Aquí es donde se establece el color de fondo
                    ) {
                        Text("Agree and continue", color = Color.White, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}