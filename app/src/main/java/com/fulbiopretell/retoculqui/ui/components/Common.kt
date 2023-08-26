package com.fulbiopretell.retoculqui.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fulbiopretell.retoculqui.R


@Composable
fun SocialLoginButton(provider: String, onClick: () -> Unit, @DrawableRes iconResId: Int) {

    val iconPainter = painterResource(id = iconResId)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(vertical = 5.dp, horizontal = 16.dp)
            .background(Color.White.copy(alpha = 0.8F), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                painter = iconPainter,
                contentDescription = "Icono de $provider",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Text(
                "Continuar con $provider",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            ) // Alineación de texto central
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedEditText(hint: String, value: String, onValueChange: (String) -> Unit) {
    var isFocused by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .background(
                color = Color.White, shape = RoundedCornerShape(5.dp)
            )
            .border(
                width = 1.dp,
                color = if (isFocused) colorResource(id = R.color.green_culqui) else Color.Transparent, // Borde verde cuando está enfocado, transparente cuando no
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        if (value.isEmpty() && !isFocused) {
            Text(
                text = hint,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp)
            )
        }

        val visualTransformation = when (hint) {
            "Password" -> PasswordVisualTransformation()
            else -> VisualTransformation.None

        }

        val keyboardType = when (hint) {
            "Password" -> KeyboardType.Password
            else -> KeyboardType.Text
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = if (isFocused) hint else "", modifier = Modifier.padding(top = 8.dp)
                )
            },
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                textColor = Color.Black,
                cursorColor = Color.Black
            ),
            singleLine = true,
            shape = RoundedCornerShape(5.dp),
            textStyle = TextStyle(color = Color.Black),
        )
    }
}