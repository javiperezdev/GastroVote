package es.dam.gastrovote.javiperez.ui.ruta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.domain.model.Bar
import es.dam.gastrovote.javiperez.ui.components.GastroScaffold
import es.dam.gastrovote.javiperez.ui.review.components.BarCard

@Composable
internal fun RutaScreen(
    bars: List<Bar>,
    modifier: Modifier = Modifier
) {
    GastroScaffold(
        modifier = modifier,
        showBanner = false
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(bars) { bar ->
                BarCard(bar, true, true)
            }
        }
    }
}
