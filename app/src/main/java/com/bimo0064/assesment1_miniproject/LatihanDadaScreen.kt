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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LatihanDadaScreen(onBack: () -> Unit) {
    var selectedLevel by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.chest_workout),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(24.dp))

        if (selectedLevel.isEmpty()) {
            BarLevelDada(title = stringResource(R.string.beginner)) { selectedLevel = "Pemula" }
            BarLevelDada(title = stringResource(R.string.intermediate)) { selectedLevel = "Menengah" }
            BarLevelDada(title = stringResource(R.string.hard)) { selectedLevel = "Sulit" }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedLevel) {
            "Pemula" -> PemulaScreen { selectedLevel = "" }
            "Menengah" -> MenengahScreen { selectedLevel = "" }
            "Sulit" -> SulitScreen { selectedLevel = "" }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (selectedLevel.isEmpty()) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clickable { onBack() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.back),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
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
fun PemulaScreen(onClose: () -> Unit) {
    WorkoutLevelScreen(
        title = stringResource(id = R.string.beginner),
        exercises = stringResource(id = R.string.beginner_exercises),
        onClose = onClose
    )
}

@Composable
fun MenengahScreen(onClose: () -> Unit) {
    WorkoutLevelScreen(
        title = stringResource(id = R.string.intermediate),
        exercises = stringResource(id = R.string.intermediate_exercises),
        onClose = onClose
    )
}

@Composable
fun SulitScreen(onClose: () -> Unit) {
    WorkoutLevelScreen(
        title = stringResource(id = R.string.hard),
        exercises = stringResource(id = R.string.hard_exercises),
        onClose = onClose
    )
}

@Composable
fun WorkoutLevelScreen(
    title: String,
    exercises: String,
    onClose: () -> Unit
) {
    var inputTime by remember { mutableStateOf("") }
    var remainingTime by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

    val restTimeError = stringResource(id = R.string.rest_time_error)
    val restTimeLabel = stringResource(id = R.string.rest_time_label)
    val startRestLabel = stringResource(id = R.string.start_rest)
    val remainingTimeLabel = stringResource(id = R.string.remaining_time)

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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(title, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(exercises, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onClose) {
            Text(stringResource(id = R.string.back_to_level))
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputTime,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    inputTime = it
                }
            },
            label = { Text(restTimeLabel) },
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
                    errorText = restTimeError
                } else {
                    remainingTime = input
                    isRunning = true
                    errorText = ""
                }
            },
            enabled = !isRunning
        ) {
            Text(startRestLabel)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("$remainingTimeLabel: ${remainingTime}s")
    }
}
