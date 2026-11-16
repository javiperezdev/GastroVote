package es.dam.gastrovote.javiperez.ui.bar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.dam.gastrovote.javiperez.data.BarRepository
import es.dam.gastrovote.javiperez.ui.components.GastroScaffold

@Composable
internal fun BarScreen(
    modifier: Modifier = Modifier,
    barId: Int
) {
    GastroScaffold(
        modifier = modifier,
        showBanner = false
    ) {
        Text(text = "Bar ${BarRepository().bars[barId].name}")
    }
}