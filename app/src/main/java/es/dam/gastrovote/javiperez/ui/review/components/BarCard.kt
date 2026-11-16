package es.dam.gastrovote.javiperez.ui.review.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.domain.model.Bar
import es.dam.gastrovote.javiperez.ui.components.GastroCard

@Composable
internal fun BarCard(
    bar: Bar,
    isFavourite: Boolean,
    isReviewed: Boolean
) {
    GastroCard {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = bar.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row() {
                    if (isFavourite) {
                        Image(
                            painter = painterResource(R.drawable.favourite),
                            contentDescription = stringResource(R.string.favourite),
                            modifier = Modifier.size(26.dp),
                        )
                    }
                    if (isReviewed) {
                        Image(
                            painter = painterResource(R.drawable.check),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            }
                // Check ui and adjust the card, I am working for the #24
            Text(
                text = bar.tapa.name,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
