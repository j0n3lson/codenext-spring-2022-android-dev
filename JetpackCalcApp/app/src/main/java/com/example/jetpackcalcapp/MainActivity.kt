package com.example.jetpackcalcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcalcapp.ui.theme.JetpackCalcAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCalcAppTheme {
                CalcApp()
            }
        }
    }
}

@Composable
private fun CalcApp() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TxtCalcArea(Modifier.weight(2f))
            ColButtonArea()
        }
    }
}

@Composable
private fun TxtCalcArea(padding: Modifier) {
    Text(text = "txtCalcArea", modifier = Modifier.padding())
}

@Composable
private fun ColButtonArea() {
    var rowMods = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    var buttonMods = Modifier.padding(horizontal = 4.dp)
    Row(modifier = rowMods) {
        for (label in listOf("AC", "( )", "%", "/")) {
            ButtonBasic(label, buttonMods)
        }
    }
    Row(modifier = rowMods) {
        for (label in listOf("7", "8", "9", "x")) {
            ButtonBasic(label, buttonMods)
        }
    }
    Row(modifier = rowMods) {
        for (label in listOf("4", "5", "6", "-")) {
            ButtonBasic(label, buttonMods)
        }
    }
    Row(modifier = rowMods) {
        for (label in listOf("1", "2", "3", "+")) {
            ButtonBasic(label, buttonMods)
        }
    }
    Row(modifier = rowMods) {
        for (label in listOf("0", ".", "xx", "=")) {
            ButtonBasic(label, buttonMods)
        }
    }
}

@Composable
private fun ButtonBasic(value: String, buttonMods: Modifier) {
    Button(onClick = { /*TODO*/ }, modifier = buttonMods) {
        Text(value)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    JetpackCalcAppTheme {
        CalcApp()
    }
}