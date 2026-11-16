package es.dam.gastrovote.javiperez.ui.bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme

@Preview
@Composable
internal fun BarPreview() {
    GastroVoteTheme {
        BarScreen(barId = 1)
    }
}