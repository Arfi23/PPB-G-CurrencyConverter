package com.example.simplecurrencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.simplecurrencyconverter.ui.theme.SimpleCurrencyConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleCurrencyConverterTheme {
                CurrencyConverterApp()
            }
        }
    }
}

@Composable
fun CurrencyConverterApp() {
    var usdAmount by remember { mutableStateOf(TextFieldValue("")) }
    var idrAmount by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }
    val conversionRate = 16500.0 // 1 USD = 16.500 IDR

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Konversi USD ↔ IDR",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = usdAmount,
            onValueChange = { usdAmount = it },
            label = { Text("Masukkan USD") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val usd = usdAmount.text.toDoubleOrNull()
                result = if (usd != null) {
                    val idr = usd * conversionRate
                    "Hasil: Rp ${"%,.0f".format(idr)}"
                } else {
                    "Masukkan nilai USD yang valid!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Konversi ke IDR")
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = idrAmount,
            onValueChange = { idrAmount = it },
            label = { Text("Masukkan IDR") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val idr = idrAmount.text.toDoubleOrNull()
                result = if (idr != null) {
                    val usd = idr / conversionRate
                    "Hasil: USD ${"%.2f".format(usd)}"
                } else {
                    "Masukkan nilai IDR yang valid!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Konversi ke USD")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = result,
            style = MaterialTheme.typography.titleLarge
        )
    }
}