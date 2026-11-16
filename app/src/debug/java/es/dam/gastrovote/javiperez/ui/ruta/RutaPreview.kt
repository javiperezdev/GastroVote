package es.dam.gastrovote.javiperez.ui.ruta

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import es.dam.gastrovote.javiperez.data.BarRepository
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme

@Preview
@Composable
internal fun BarPreview() {
    GastroVoteTheme {
        val bars = BarRepository().bars
            RutaScreen(bars = bars)
    }
}