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
fun LatihanBahuScreen(onBack: () -> Unit) {
    var selectedLevel by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Latihan Bahu", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(24.dp))

        if (selectedLevel.isEmpty()) {
            BarLevelBahu("Pemula") { selectedLevel = "Pemula" }
            BarLevelBahu("Menengah") { selectedLevel = "Menengah" }
            BarLevelBahu("Sulit") { selectedLevel = "Sulit" }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedLevel) {
            "Pemula" -> BahuPemulaScreen(onBack) { selectedLevel = "" }
            "Menengah" -> BahuMenengahScreen(onBack) { selectedLevel = "" }
            "Sulit" -> BahuSulitScreen(onBack) { selectedLevel = "" }
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
fun BarLevelBahu(title: String, onClick: () -> Unit) {
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
fun BahuPemulaScreen(onBack: () -> Unit, onClose: () -> Unit) {
    var inputTime by remember { mutableStateOf("") }
    var remainingTime by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

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
            text = "1. Shoulder Press dengan Botol\n\n2. Lateral Raise Tanpa Beban\n\n3. Front Raise Tanpa Beban\n\n4. Shoulder Stretch",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Kembali ke Pilihan")
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputTime,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    inputTime = it
                }
            },
            label = { Text("Waktu Istirahat (detik)") },
            singleLine = true,
            isError = errorText.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorText.isNotEmpty()) {
            Text(errorText, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val input = inputTime.toIntOrNull()
                if (input == null || input < 1 || input > 299) {
                    errorText = "Masukkan waktu antara 1 dan 299 detik"
                } else {
                    remainingTime = input
                    isRunning = true
                    errorText = ""
                }
            },
            enabled = !isRunning
        ) {
            Text("Mulai Istirahat")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Waktu Istirahat: ${remainingTime}s")
    }
}

@Composable
fun BahuMenengahScreen(onBack: () -> Unit, onClose: () -> Unit) {
    var inputTime by remember { mutableStateOf("") }
    var remainingTime by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

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
            text = "1. Pike Push-up\n\n2. Plank to Downward Dog\n\n3. Wall Walk\n\n4. Arm Circles",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Kembali ke Pilihan")
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputTime,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    inputTime = it
                }
            },
            label = { Text("Waktu Istirahat (detik)") },
            singleLine = true,
            isError = errorText.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorText.isNotEmpty()) {
            Text(errorText, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val input = inputTime.toIntOrNull()
                if (input == null || input < 1 || input > 299) {
                    errorText = "Masukkan waktu antara 1 dan 299 detik"
                } else {
                    remainingTime = input
                    isRunning = true
                    errorText = ""
                }
            },
            enabled = !isRunning
        ) {
            Text("Mulai Istirahat")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Waktu Istirahat: ${remainingTime}s")
    }
}

@Composable
fun BahuSulitScreen(onBack: () -> Unit, onClose: () -> Unit) {
    var inputTime by remember { mutableStateOf("") }
    var remainingTime by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

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
            text = "1. Handstand Push-up\n\n2. Clap Push-up\n\n3. Dive Bomber Push-up\n\n4. Single-arm Plank",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Kembali ke Pilihan")
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputTime,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    inputTime = it
                }
            },
            label = { Text("Waktu Istirahat (detik)") },
            singleLine = true,
            isError = errorText.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorText.isNotEmpty()) {
            Text(errorText, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val input = inputTime.toIntOrNull()
                if (input == null || input < 1 || input > 299) {
                    errorText = "Masukkan waktu antara 1 dan 299 detik"
                } else {
                    remainingTime = input
                    isRunning = true
                    errorText = ""
                }
            },
            enabled = !isRunning
        ) {
            Text("Mulai Istirahat")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Waktu Istirahat: ${remainingTime}s")
    }
}