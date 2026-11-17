package es.dam.gastrovote.javiperez.ui.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.RutaActivity
import es.dam.gastrovote.javiperez.ui.components.GastroButton
import es.dam.gastrovote.javiperez.ui.components.GastroScaffold
import es.dam.gastrovote.javiperez.ui.components.RouteProgress


@Composable
internal fun HomeScreen(
    username: String,
    reviewedCount: Int,
    totalBars: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    GastroScaffold(
        modifier = modifier,
        scrollable = true
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


        Row(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "* Connected to wifi",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        GastroButton(
            onClick = {
                val intent = Intent(
                    context,
                    RutaActivity::class.java
                )
                context.startActivity(intent)
            },
            buttonContent = stringResource(R.string.see_route),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
    }
}