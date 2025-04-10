package com.bimo0064.assesment1_miniproject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LatihanDadaScreen(onBack: () -> Unit) {
    var selectedLevel by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Latihan Dada", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(24.dp))

        if (selectedLevel.isEmpty()) {
            BarLevelDada("Pemula") { selectedLevel = "Pemula" }
            BarLevelDada("Menengah") { selectedLevel = "Menengah" }
            BarLevelDada("Sulit") { selectedLevel = "Sulit" }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedLevel) {
            "Pemula" -> PemulaScreen(onBack) { selectedLevel = "" }
            "Menengah" -> MenengahScreen(onBack) { selectedLevel = "" }
            "Sulit" -> SulitScreen(onBack) { selectedLevel = "" }
            else -> {}
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .clickable { onBack() },
            contentAlignment = Alignment.Center
        ) {
            Text("Kembali", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BarLevelDada(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(350.dp)
            .height(150.dp)
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
fun PemulaScreen(onBack: () -> Unit, onClose: () -> Unit) {
    var remainingTime by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (remainingTime > 0) {
                kotlinx.coroutines.delay(1000)
                remainingTime--
            }
            isRunning = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Pemula", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "1. Push up \n\n2. Incline Push up \n\n3. Kneeling Push up \n\n4. Chest Stretch",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Kembali ke Pilihan")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Waktu Istirahat: ${remainingTime}s")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                remainingTime = 60
                isRunning = true
            },
            enabled = !isRunning
        ) {
            Text("Mulai Istirahat")
        }
    }
}


@Composable
fun MenengahScreen(onBack: () -> Unit, onClose: () -> Unit) {
    var remainingTime by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (remainingTime > 0) {
                kotlinx.coroutines.delay(1000)
                remainingTime--
            }
            isRunning = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Menengah", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("1. Decline Push up \n\n2. Wide Grip Push up \n\n3. Plyometric Push up \n\n4. Archer Push up", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Kembali ke Pilihan")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Waktu Istirahat: ${remainingTime}s")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                remainingTime = 60
                isRunning = true
            },
            enabled = !isRunning
        ) {
            Text("Mulai Istirahat")
        }
    }
}


@Composable
fun SulitScreen(onBack: () -> Unit, onClose: () -> Unit) {
    var remainingTime by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (remainingTime > 0) {
                kotlinx.coroutines.delay(1000)
                remainingTime--
            }
            isRunning = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Sulit", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("1. One-arm Push up \n\n2. Clap Push up \n\n3. Spiderman Push up \n\n4. Handstand Push up", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Kembali ke Pilihan")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Waktu Istirahat: ${remainingTime}s")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                remainingTime = 60
                isRunning = true
            },
            enabled = !isRunning
        ) {
            Text("Mulai Istirahat")
        }
    }
}
