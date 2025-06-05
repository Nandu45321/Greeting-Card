package com.nv.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nv.greetingcard.ui.theme.GreetingCardTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import com.nv.greetingcard.ui.NavRoutes
import com.nv.greetingcard.ScreenOne
import com.nv.greetingcard.ScreenTwo
import com.nv.greetingcard.ScreenThree
import com.nv.greetingcard.BusinessCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.SCREEN_MAIN) {
        composable(NavRoutes.SCREEN_MAIN) {
            MainScreen(navController = navController)
        }
        composable(NavRoutes.SCREEN_ONE) {
            ScreenOne(navController = navController) // Call the new composable
        }
        composable(NavRoutes.SCREEN_TWO) {
            ScreenTwo(navController = navController) // Call the new composable
        }
        composable(NavRoutes.SCREEN_THREE) {
            ScreenThree(navController = navController) // Call the new composable
        }
        composable(NavRoutes.SCREEN_FOUR) {
            BusinessCard(navController = navController) // Call the new composable
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: androidx.navigation.NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Greeting Card") }, // Optional title
                actions = {
                    IconButton(onClick = { navController.navigate(NavRoutes.SCREEN_ONE) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward, // Using a built-in icon
                            contentDescription = "Next Screen"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer, // Example color
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        GreetingImage(
            message = stringResource(R.string.happy_birthday_text),
            from = stringResource(R.string.signature_text),
            modifier = Modifier.padding(innerPadding) // Apply padding from Scaffold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyScreen(
    screenTitle: String,
    navController: androidx.navigation.NavController,
    nextRoute: String?,
    previousRoute: String?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(screenTitle) },
                navigationIcon = {
                    if (previousRoute != null) { // Show back button if there's a previous screen
                        IconButton(onClick = { navController.popBackStack() }) { // Or navController.navigate(previousRoute) with popUpTo logic
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                actions = {
                    if (nextRoute != null) { // Show forward button if there's a next screen
                        IconButton(onClick = { navController.navigate(nextRoute) }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Next Screen"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Apply padding from Scaffold
                .padding(16.dp), // Additional padding for content
            contentAlignment = Alignment.Center
        ) {
            Text(text = "This is $screenTitle", fontSize = 24.sp)
        }
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 72.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 28.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) { // Keep this as is
    val image = painterResource(R.drawable.androidparty)
    Box(modifier) { // The modifier passed here will include padding from Scaffold
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.65F
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp) // This padding is internal to GreetingImage's content
        )
    }
}

@Preview(
    showBackground = true,
    // name = "My Preview",
    // showSystemUi = true,
    )
@Composable
fun BirthdayCardPreview() {
    GreetingCardTheme {
        AppNavigation()
    }
}

