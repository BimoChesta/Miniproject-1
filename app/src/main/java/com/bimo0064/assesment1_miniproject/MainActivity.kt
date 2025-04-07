package com.bimo0064.assesment1_miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bimo0064.assesment1_miniproject.ui.theme.Assesment1_miniprojectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assesment1_miniprojectTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var showAbout by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBarWithMenu(onAboutClick = { showAbout = true })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showAbout) {
                AboutScreen(onClose = { showAbout = false })
            } else {
                Greeting(name = "Bimo")
                Spacer(modifier = Modifier.height(16.dp))
                for (i in 1..4) {
                    BarItem(index = i)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithMenu(onAboutClick: () -> Unit) {
    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = "Aplikasi Home Workout",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Tentang Aplikasi") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Tentang"
                            )
                        },
                        onClick = {
                            menuExpanded = false
                            onAboutClick()
                        }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Black
        )
    )
}

@Composable
fun AboutScreen(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Tentang Aplikasi", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Aplikasi ini dirancang Oleh\nBimo Chesta Adabi\n607062300064\nUntuk membantu pengguna melakukan fitness dari rumah.")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Kembali",
            color = Color.Blue,
            modifier = Modifier.clickable(onClick = onClose)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!, ingin latihan apa hari ini?",
        modifier = modifier.padding(16.dp),
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BarItem(index: Int) {
    Box(
        modifier = Modifier
            .width(350.dp)
            .height(150.dp)
            .padding(vertical = 8.dp)
            .background(Color.Black, shape = MaterialTheme.shapes.medium)
            .shadow(4.dp, shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Latihan $index",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assesment1_miniprojectTheme {
        Greeting("Bimo")
    }
}
