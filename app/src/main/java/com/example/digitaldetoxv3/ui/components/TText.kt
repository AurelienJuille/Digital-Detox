package com.example.digitaldetoxv3.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun TText (text: Int) {
    val str = stringResource(text)
    Text(text = str)
}