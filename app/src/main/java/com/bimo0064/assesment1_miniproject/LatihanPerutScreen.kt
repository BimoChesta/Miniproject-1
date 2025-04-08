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
fun LatihanPerutScreen(onBack: () -> Unit) {
    var selectedLevel by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Latihan Perut", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(24.dp))

        if (selectedLevel.isEmpty()) {
            BarLevelPerut("Pemula") { selectedLevel = "Pemula" }
            BarLevelPerut("Menengah") { selectedLevel = "Menengah" }
            BarLevelPerut("Sulit") { selectedLevel = "Sulit" }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedLevel) {
            "Pemula" -> PerutPemulaScreen(onBack) { selectedLevel = "" }
            "Menengah" -> PerutMenengahScreen(onBack) { selectedLevel = "" }
            "Sulit" -> PerutSulitScreen(onBack) { selectedLevel = "" }
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
fun BarLevelPerut(title: String, onClick: () -> Unit) {
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
fun PerutPemulaScreen(onBack: () -> Unit, onClose: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Latihan Pemula", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Latihan yang disarankan: Push-up, Dumbbell Press, Chest Stretch.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Kembali ke Pilihan")
        }
    }
}

@Composable
fun PerutMenengahScreen(onBack: () -> Unit, onClose: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Latihan Menengah", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Latihan yang disarankan: Incline Dumbbell Press, Cable Fly, Dips.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Kembali ke Pilihan")
        }
    }
}

@Composable
fun PerutSulitScreen(onBack: () -> Unit, onClose: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Latihan Sulit", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Latihan yang disarankan: Barbell Bench Press, Plyometric Push-ups, Weighted Dips.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Kembali ke Pilihan")
        }
    }
}