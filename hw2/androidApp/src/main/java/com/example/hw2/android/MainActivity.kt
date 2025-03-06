package com.example.hw2.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hw2.Counter

class MainActivity : ComponentActivity() {
    private val cntr = Counter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterApp(cntr)
                }
            }
        }
    }
}

@Composable
fun CounterApp(cntr: Counter) {
    // Состояние счётчика
    var value by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Отображение счётчика
        Text(
            text = "Счётчик: ${value}",
            modifier = Modifier.padding(16.dp)
        )

        // Кнопка для увеличения счётчика
        Button(
            onClick = { value =  cntr.Increment() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("+1")
        }

        // Кнопка для уменьшения счётчика
        Button(
            onClick = { value = cntr.Decrement() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("-1")
        }

        // Кнопка для присвоения случайного числа
        Button(
            onClick = { value = cntr.Random() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Случайное число")
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        CounterApp(Counter())
    }
}
