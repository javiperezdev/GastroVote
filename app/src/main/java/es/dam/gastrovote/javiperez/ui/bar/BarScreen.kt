package es.dam.gastrovote.javiperez.ui.bar

import android.content.Intent
import android.content.Intent.ACTION_SEND
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.data.BarRepository
import es.dam.gastrovote.javiperez.domain.calculatePrice
import es.dam.gastrovote.javiperez.ui.asString
import es.dam.gastrovote.javiperez.ui.components.GastroButton
import es.dam.gastrovote.javiperez.ui.components.GastroScaffold
import es.dam.gastrovote.javiperez.ui.components.SectionTitle
import es.dam.gastrovote.javiperez.ui.components.SummaryLine
import es.dam.gastrovote.javiperez.ui.mapRatingToComment
import es.dam.gastrovote.javiperez.ui.review.components.TapaCard

@Composable
internal fun BarScreen(
    modifier: Modifier = Modifier,
    barId: Int
) {
    GastroScaffold(
        modifier = modifier,
        showBanner = false
    ) {
        val bar = BarRepository().bars[barId]
        val locale = LocalConfiguration.current.locales[0] // Get the first country selected in the device
        val context = LocalContext.current
        val shareTapa = stringResource(R.string.share_tapa, bar.tapa.name, bar.name)

        TapaCard(
            tapa = bar.tapa,
            isFavourite = true, //mocked
            onFavouriteChange = {}, // not working currently
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            SummaryLine("${bar.address} - ${bar.phone}")

            Column() {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GastroButton(
                        modifier = Modifier.weight(0.5f),
                        buttonContent = stringResource(R.string.bar_call),
                        color = MaterialTheme.colorScheme.primary,
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = "tel:${bar.phone}".toUri()
                            context.startActivity(intent)
                        }
                    )
                    GastroButton(
                        modifier = Modifier.weight(0.5f),
                        buttonContent = stringResource(R.string.bar_web),
                        color = MaterialTheme.colorScheme.primary,
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = bar.web.toUri()
                            context.startActivity(intent)
                        }
                    )
                }

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GastroButton(
                        modifier = Modifier.weight(0.5f),
                        buttonContent = stringResource(R.string.bar_location),
                        color = MaterialTheme.colorScheme.primary,
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = "geo:0,0?q=${bar.address}".toUri()
                            context.startActivity(intent)
                        }
                    )
                    GastroButton(
                        modifier = Modifier.weight(0.5f),
                        buttonContent = stringResource(R.string.bar_share),
                        color = MaterialTheme.colorScheme.primary,
                        onClick = {
                            val intent = Intent().apply {
                            action = ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, shareTapa)
                            type = "text/plain"
                        }

                            val shareIntent = Intent.createChooser(intent, null)
                            context.startActivity(shareIntent)}
                    )
                }
            }

            SectionTitle(text = stringResource(R.string.your_review))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SummaryLine("${stringResource(R.string.size)} ${bar.tapa.size.asString()}")
                SummaryLine(
                    "${stringResource(R.string.rating)} " +
                        stringResource(
                            R.string.rating_with_comment,
                            bar.tapa.rating,
                            stringResource(mapRatingToComment(bar.tapa.rating))
                        )
                )
                // isStudent currently mocked to false
                SummaryLine(
                    "${stringResource(R.string.summary_final_price)} ${
                        stringResource(
                            R.string.price_format,
                            "%.2f".format(
                                locale,
                                calculatePrice(bar.tapa.price, false, tapaSize = bar.tapa.size)
                            )
                        )
                    }"
                )
            }

            GastroButton(
                modifier = Modifier.fillMaxWidth(),
                buttonContent = stringResource(R.string.write_review),
                color = MaterialTheme.colorScheme.secondary,
                onClick = {}
            )
        }
    }
}