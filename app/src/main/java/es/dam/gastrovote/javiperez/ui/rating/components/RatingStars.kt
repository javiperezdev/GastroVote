package es.dam.gastrovote.javiperez.ui.rating.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
internal fun StarRow(
    rating: Int,
    starSize: TextUnit,
    modifier: Modifier = Modifier,
) {
    var filled = false

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        for(i in 1..5) {
            if (i < rating) {
                filled = true
            }

            Text(
                text = if (filled) "★" else "☆",
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 2.dp),
                fontSize = starSize,
                color = if (filled) {
                    MaterialTheme.colorScheme.tertiary
                } else {
                    MaterialTheme.colorScheme.outline
                }
            )

            filled = false
        }
    }
}