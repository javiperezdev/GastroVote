package es.dam.gastrovote.javiperez.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.ui.components.GastroButton
import es.dam.gastrovote.javiperez.ui.components.RouteProgress


@Composable
internal fun HomeScreen(
    username: String,
    reviewedCount: Int,
    totalBars: Int,
    onSeeRuta: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(R.drawable.gastro_vote_banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 240.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                    )
                )
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (username.isBlank()) {
                    stringResource(R.string.home_greeting_anonymous)
                } else {
                    stringResource(R.string.home_greeting, username)
                },
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            RouteProgress(reviewedBars = reviewedCount, totalBars = totalBars)

            GastroButton(
                onClick = onSeeRuta,
                buttonContent = stringResource(R.string.see_route),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}