package com.example.compose

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val configuration = LocalConfiguration.current
            val landscapeMode = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Image(
                        painter = painterResource(id = R.drawable.bg),
                        contentDescription = "Background",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_login_header),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(if (landscapeMode) 50.dp else 200.dp)
                    )
                    Text(
                        stringResource(id = R.string.login),
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif

                        ),
                        modifier = Modifier
                            .padding(top = if (landscapeMode) 0.dp else 10.dp),
                    )
                    EmailTextField()
                    PasswordTextField()
                    RoundedButton(text = stringResource(id = R.string.login_button_text)) {}
                    Row(
                        modifier = Modifier
                            .padding(top = if (landscapeMode) 0.dp else 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        SocialMediaImage(resourceId = R.drawable.ic_login_fb)
                        SocialMediaImage(resourceId = R.drawable.ic_login_google)
                        SocialMediaImage(resourceId = R.drawable.ic_login_ig)

                    }
                }
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField() {
    var email by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val landscapeMode = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text(stringResource(id = R.string.email_hint)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
        modifier = Modifier
            .padding(horizontal = if (landscapeMode) 48.dp else 24.dp)
            .fillMaxWidth(),
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField() {
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val landscapeMode = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(stringResource(id = R.string.password_hint)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),

        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "Password Visibility Toggle"
                )
            }
        },
        modifier = Modifier
            .padding(horizontal = if (landscapeMode) 48.dp else 24.dp)
            .fillMaxWidth(),

        )
}

@Composable
fun RoundedButton(text: String, onClick: () -> Unit) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val landscapeMode = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    Button(
        onClick = {
            onClick()
            Toast.makeText(context, "Button Clicked!", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (landscapeMode) 72.dp else 48.dp)
            .padding(top = if (landscapeMode) 0.dp else 20.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SocialMediaImage(
    resourceId: Int,
    contentDescription: String? = null
) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(60.dp)
            .padding(8.dp)
    )
}







