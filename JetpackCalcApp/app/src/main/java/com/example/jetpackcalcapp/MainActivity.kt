package com.example.jetpackcalcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
            JetPackCalcApp()
        }
    }
}

@Composable
fun JetPackCalcApp() {
    JetpackCalcAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column() {
                TxtCalcArea()
                ColButtonArea()
            }
        }
    }
}

@Composable
fun TxtCalcArea() {
    Text(text = "txtCalcArea")
}

@Composable
fun ColButtonArea() {
    Row() {
        ButtonBasic("AC")
        ButtonBasic("( )")
        ButtonBasic("%")
        ButtonBasic("/")
    }
    Row() {
        ButtonBasic("7")
        ButtonBasic("8")
        ButtonBasic("9")
        ButtonBasic("x")
    }

    Row() {
        ButtonBasic("4")
        ButtonBasic("5")
        ButtonBasic("6")
        ButtonBasic("-")
    }
    Row() {
        ButtonBasic("1")
        ButtonBasic("2")
        ButtonBasic("3")
        ButtonBasic("+")
    }
    Row() {
        ButtonBasic("0")
        ButtonBasic(".")
        ButtonBasic("xx")
        ButtonBasic("=")
    }
}

@Composable
fun ButtonBasic(value: String) {
    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(16.dp)) {
        Text(value)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackCalcApp()
}