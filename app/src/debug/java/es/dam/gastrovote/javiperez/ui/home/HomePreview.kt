package es.dam.gastrovote.javiperez.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme


@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 1800
)
@Composable
internal fun HomeScreenPreview() {
    GastroVoteTheme(darkTheme = false) {
        HomeScreen(
            username = "Lucía",
            reviewedCount = 3,
            totalBars = 8,
            onSeeRuta = {}
        )
    }
}
