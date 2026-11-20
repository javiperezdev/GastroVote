package es.dam.gastrovote.javiperez.ui.ruta.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.BarActivity
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.domain.model.Bar
import es.dam.gastrovote.javiperez.ui.components.GastroCard
import es.dam.gastrovote.javiperez.ui.components.SummaryLine
import kotlin.jvm.java

@Composable
internal fun BarCard(
    bar: Bar,
    isFavourite: Boolean,
    isReviewed: Boolean
) {
    GastroCard {

        val context = LocalContext.current

        Column(
            modifier = Modifier
                .clickable(onClick = {
                    val intent = Intent(context, BarActivity::class.java)
                    intent.putExtra("BAR_ID", bar.id - 1)
                    context.startActivity(intent)
                })
                .padding(12.dp)
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
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

            SummaryLine(bar.tapa.name)
        }
    }
}
