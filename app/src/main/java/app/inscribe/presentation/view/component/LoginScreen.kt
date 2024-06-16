package app.inscribe.presentation.view.component

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.inscribe.presentation.view.ui.theme.InscribeTheme
import app.inscribe.R
import app.inscribe.presentation.view.InscribeHome
import app.inscribe.presentation.viewmodel.LoginViewModel
import app.inscribe.presentation.view.ui.theme.Typography

@Composable
fun LoginScreen(onGoogleSignInClick: () -> Unit, viewModel: LoginViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when (uiState) {
            is LoginViewModel.UiState.LoggedOut -> {
                // Show error toast
                Toast.makeText(context, "Login failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
            is LoginViewModel.UiState.LoggedIn -> {
                // Navigate to the next activity
                val intent = Intent(context, InscribeHome::class.java)
                context.startActivity(intent)
            }
            else -> {
                // Do nothing for now
                Toast.makeText(context, "Login failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.login_png),
            contentDescription = null,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .padding(20.dp, 30.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = "Welcome to Inscribe!",
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            fontSize = 24.sp,
            style = Typography.titleLarge
        )
        Text(
            text = "Discover blog platform, reading, commenting, and sharing blogs. Engage with a community of storytellers, explore diverse perspectives, and connect effortlessly through insightful conversations.",
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            style = Typography.titleSmall,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyCard(onGoogleSignInClick)
        Text(
            text = "Skip",
            color = Color.Black,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp)
                .clickable {
                    val intent = Intent(context, InscribeHome::class.java)
                    context.startActivity(intent)
                },
            fontSize = 16.sp,
            style = Typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    InscribeTheme {
        LoginScreen(onGoogleSignInClick = {})
    }
}

@Composable
fun MyCard(onGoogleSignInClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(0.dp, 20.dp)
            .fillMaxWidth(),
        onClick = onGoogleSignInClick,
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Sign in with google",
                fontSize = 16.sp,
                color = Color.White,
                style = Typography.titleSmall
            )
        }
    }


}
