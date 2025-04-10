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
import kotlinx.coroutines.delay

@Composable
fun LatihanKakiScreen(onBack: () -> Unit) {
    var selectedLevel by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Latihan Kaki", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(24.dp))

        if (selectedLevel.isEmpty()) {
            BarLevelKaki("Pemula") { selectedLevel = "Pemula" }
            BarLevelKaki("Menengah") { selectedLevel = "Menengah" }
            BarLevelKaki("Sulit") { selectedLevel = "Sulit" }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedLevel) {
            "Pemula" -> KakiPemulaScreen(onBack) { selectedLevel = "" }
            "Menengah" -> KakiMenengahScreen(onBack) { selectedLevel = "" }
            "Sulit" -> KakiSulitScreen(onBack) { selectedLevel = "" }
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
fun BarLevelKaki(title: String, onClick: () -> Unit) {
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
fun KakiPemulaScreen(onBack: () -> Unit, onClose: () -> Unit) {
    var remainingTime by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (remainingTime > 0) {
                delay(1000)
                remainingTime--
            }
            isRunning = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Pemula", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "1. Bodyweight Squat\n\n2. Lunges\n\n3. Glute Bridges\n\n4. Calf Raises",
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
fun KakiMenengahScreen(onBack: () -> Unit, onClose: () -> Unit) {
    var remainingTime by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (remainingTime > 0) {
                delay(1000)
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
        Text(
            text = "1. Jump Squats\n\n2. Bulgarian Split Squat\n\n3. Step-ups\n\n4. Single-leg Deadlift",
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
fun KakiSulitScreen(onBack: () -> Unit, onClose: () -> Unit) {
    var remainingTime by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (remainingTime > 0) {
                delay(1000)
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
        Text(
            text = "1. Pistol Squat\n\n2. Box Jumps\n\n3. Barbell Squats\n\n4. Sumo Deadlift",
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
