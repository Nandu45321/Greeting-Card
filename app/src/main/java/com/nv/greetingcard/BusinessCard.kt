package com.nv.greetingcard // Or your actual package name

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nv.greetingcard.ui.NavRoutes
import com.nv.greetingcard.ui.theme.GreetingCardTheme

// No NavRoutes import needed for forward navigation here, but good for consistency if you add it

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessCard(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Screen Three") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                // No forward action from Screen Three
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) {innerPadding ->
        BusinessCardContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BusinessCardContent(modifier: Modifier) { // modifier here is Modifier.padding(innerPadding)
    Column(
        modifier = modifier // Apply the Scaffold's padding modifier here
            .fillMaxSize()     // Make this Column fill the available space after padding
            .background(Color.Gray) // Added for visualization during debugging
    ) {
        PrimaryContent(
            modifier = Modifier
                .weight(1f) // PrimaryContent takes up available space
                .fillMaxWidth() // Ensure it spans the width
        )
        SecondaryContent(
            modifier = Modifier
                // .weight(1f) // If you want them to share space equally
                .fillMaxWidth() // Ensure it spans the width
        )
    }
}

@Composable
fun PrimaryContent(modifier: Modifier) {
    val androidImage = painterResource(R.drawable.android_logo)
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(16.dp, 16.dp, 16.dp, 36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = androidImage,
            contentDescription = "Android Logo",
            Modifier.size(164.dp)
        )
        Text(
            text = "Nandu Navadeep",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Android Developer - Beginner",
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SecondaryContent(modifier: Modifier) {
    Column(
        modifier = modifier
            .background(Color.LightGray)
            .padding(16.dp, 28.dp, 16.dp, 28.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "yamulapalli24@iisertvm.ac.in",
                textAlign = TextAlign.Center,
            )
        }
        Row {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "yamulapalli24@iisertvm.ac.in",
                textAlign = TextAlign.Center,
            )
        }
        Row {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "yamulapalli24@iisertvm.ac.in",
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Preview(
    showBackground = true,
    // name = "My Preview",
    // showSystemUi = true,
)
@Composable
fun BusinessCardPreview() {
    GreetingCardTheme {
        BusinessCard(navController = NavController(context = LocalContext.current))
    }
}