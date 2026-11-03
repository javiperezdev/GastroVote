package es.dam.gastrovote.javiperez.ui.rating.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.ui.theme.Calypso
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue20

@Composable
internal fun RouteProgress(
    reviewedBars: Int,
    totalBars: Int
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = stringResource(
                R.string.route_progress,
                reviewedBars.toString(),
                totalBars.toString()
            ),
            style = MaterialTheme.typography.labelMedium,
            color = Calypso
        )

        LinearProgressIndicator(
            progress = { reviewedBars.toFloat() / totalBars },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp)),
            color = CornflowerBlue,
            trackColor = CornflowerBlue20
        )
    }
}
