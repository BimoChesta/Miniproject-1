package com.bimo0064.assesment1_miniproject

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bimo0064.assesment1_miniproject.ui.theme.Assesment1_miniprojectTheme
import java.util.Locale

class MainActivity : ComponentActivity() {

    companion object {
        const val PREFS_NAME = "app_prefs"
        const val KEY_LANGUAGE = "app_language"
    }

    override fun attachBaseContext(newBase: Context) {
        val prefs = newBase.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val lang = prefs.getString(KEY_LANGUAGE, "en") ?: "en"
        val context = LocaleContextWrapper.wrap(newBase, lang)
        super.attachBaseContext(context)
    }

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

    val context = LocalContext.current
    val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
    var selectedLanguage by rememberSaveable {
        mutableStateOf(prefs.getString(MainActivity.KEY_LANGUAGE, "en") ?: "en")
    }

    Assesment1_miniprojectTheme(darkTheme = isDarkTheme) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AppBarWithMenu(
                    onAboutClick = { showAbout = true },
                    onBackToHome = { currentScreen = "home" },
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = !isDarkTheme },
                    onChangeLanguage = { lang ->
                        prefs.edit().putString(MainActivity.KEY_LANGUAGE, lang).apply()
                        selectedLanguage = lang
                        (context as Activity).recreate()
                    }
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
    onToggleTheme: () -> Unit,
    onChangeLanguage: (String) -> Unit
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
                    DropdownMenuItem(
                        text = { Text("English") },
                        onClick = {
                            menuExpanded = false
                            onChangeLanguage("en")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Indonesia") },
                        onClick = {
                            menuExpanded = false
                            onChangeLanguage("id")
                        }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.LightGray
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
    BarItem(stringResource(id = R.string.chest_workout), onClick = onLatihanDadaClick)
    BarItem(stringResource(id = R.string.shoulder_workout), onClick = onLatihanBahuClick)
    BarItem(stringResource(id = R.string.arm_workout), onClick = onLatihanTanganClick)
    BarItem(stringResource(id = R.string.leg_workout), onClick = onLatihanKakiClick)
    BarItem(stringResource(id = R.string.abs_workout), onClick = onLatihanPerutClick)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.greeting_message),
        modifier = modifier.padding(16.dp),
        color = Color.Gray,
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

@Composable
fun updateLocale(context: Context, languageCode: String): Context {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = context.resources.configuration
    config.setLocale(locale)

    return context.createConfigurationContext(config)
}
