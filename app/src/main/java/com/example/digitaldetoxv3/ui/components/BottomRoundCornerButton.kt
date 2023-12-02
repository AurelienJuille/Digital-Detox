package com.example.digitaldetoxv3.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomRoundCornerButton (text: String, onclick: () -> Unit, modifier: Modifier) {
    Button(
        modifier = modifier
            .padding(bottom = 20.dp)
            .height(64.dp)
            .clip(RoundedCornerShape(48.dp)),
        onClick = onclick,
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 50.dp),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold) // Text size and bold
        )
    }
}
