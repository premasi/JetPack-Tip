package com.training.jetpacktip.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.training.jetpacktip.R

val buttonSize: Modifier = Modifier.size(40.dp)

@Composable
fun RoundButton(
    modifier: Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color = colorResource(R.color.navy_blue).copy(alpha = 0.8F),
    backgroundColor: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background),
    elevation: CardElevation = CardDefaults.cardElevation(4.dp)
){
    Card(
        modifier = modifier
            .padding(all = 4.dp)
            .clickable { onClick.invoke() }.then(buttonSize),
        shape = CircleShape,
        colors = backgroundColor,
        elevation = elevation
    ) {
        Box (
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = imageVector,
                contentDescription = "Illustration",
                tint = tint
            )
        }

    }
}