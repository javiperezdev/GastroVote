package es.dam.gastrovote.javiperez.ui.rating.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.ui.theme.Calypso
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue
import es.dam.gastrovote.javiperez.ui.theme.Zircon

@Composable
internal fun BarSelector(
    bar: String,
    position: Int,
    total: Int,
    onNextBar: () -> Unit,
    onPreviousBar: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.choose_bar),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall,
            color = CornflowerBlue
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ArrowButton(
                iconRes = R.drawable.arrow_left,
                contentDescription = stringResource(R.string.previous_bar),
                onClick = onPreviousBar
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = bar,
                    style = MaterialTheme.typography.titleMedium,
                    color = Calypso,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(
                        R.string.bar_position,
                        position.toString(),
                        total.toString()
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = CornflowerBlue
                )
            }

            ArrowButton(
                iconRes = R.drawable.arrow_right,
                contentDescription = stringResource(R.string.next_bar),
                onClick = onNextBar
            )
        }
    }
}

@Composable
private fun ArrowButton(
    iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    FilledTonalButton(
        colors = ButtonDefaults.filledTonalButtonColors(containerColor = Zircon),
        elevation = ButtonDefaults.filledTonalButtonElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, CornflowerBlue),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = contentDescription
        )
    }
}
