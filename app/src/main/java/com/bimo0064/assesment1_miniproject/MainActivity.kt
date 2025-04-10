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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.bimo0064.assesment1_miniproject.R
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
    var currentScreen by remember { mutableStateOf("home") }
    var isDarkTheme by remember { mutableStateOf(false) }

    Assesment1_miniprojectTheme(darkTheme = isDarkTheme) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AppBarWithMenu(
                    onAboutClick = { showAbout = true },
                    onBackToHome = { currentScreen = "home" },
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = !isDarkTheme }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when {
                    showAbout -> AboutScreen(onClose = { showAbout = false })
                    currentScreen == "dada" -> LatihanDadaScreen(onBack = { currentScreen = "home" })
                    currentScreen == "bahu" -> LatihanBahuScreen(onBack = { currentScreen = "home" })
                    currentScreen == "tangan" -> LatihanTanganScreen(onBack = { currentScreen = "home" })
                    currentScreen == "kaki" -> LatihanKakiScreen(onBack = { currentScreen = "home" })
                    currentScreen == "perut" -> LatihanPerutScreen(onBack = { currentScreen = "home" })
                    else -> HomeScreen(
                        onLatihanDadaClick = { currentScreen = "dada" },
                        onLatihanBahuClick = { currentScreen = "bahu" },
                        onLatihanTanganClick = { currentScreen = "tangan" },
                        onLatihanKakiClick = { currentScreen = "kaki" },
                        onLatihanPerutClick = { currentScreen = "perut" }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithMenu(
    onAboutClick: () -> Unit,
    onBackToHome: () -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_title),
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
                        text = { Text(stringResource(id = R.string.menu_about)) },
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
                    DropdownMenuItem(
                        text = {
                            Text(
                                if (isDarkTheme)
                                    stringResource(id = R.string.theme_light)
                                else
                                    stringResource(id = R.string.theme_dark)
                            )
                        },
                        onClick = {
                            menuExpanded = false
                            onToggleTheme()
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
fun HomeScreen(
    onLatihanDadaClick: () -> Unit,
    onLatihanBahuClick: () -> Unit,
    onLatihanTanganClick: () -> Unit,
    onLatihanKakiClick: () -> Unit,
    onLatihanPerutClick: () -> Unit
) {
    Greeting(name = "Bimo")
    Spacer(modifier = Modifier.height(16.dp))
    BarItem("Latihan Dada", onClick = onLatihanDadaClick)
    BarItem("Latihan Bahu", onClick = onLatihanBahuClick)
    BarItem("Latihan Tangan", onClick = onLatihanTanganClick)
    BarItem("Latihan Kaki", onClick = onLatihanKakiClick)
    BarItem("Latihan Perut", onClick = onLatihanPerutClick)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Ayo mulai latihan hari ini",
        modifier = modifier.padding(16.dp),
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BarItem(title: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .width(350.dp)
            .height(130.dp)
            .padding(vertical = 8.dp)
            .background(Color.Black, shape = MaterialTheme.shapes.medium)
            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AboutScreen(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.about_app_title),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.about_app_description),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.back),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(onClick = onClose)
        )
    }
}
