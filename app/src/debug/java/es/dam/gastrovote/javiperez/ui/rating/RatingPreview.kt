package es.dam.gastrovote.javiperez.ui.rating

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import es.dam.gastrovote.javiperez.domain.model.Tapa
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme

data class RatingPreviewState(
    val finalPrice: Double,
    val liked: Boolean,
    val isStudent: Boolean
)

class RatingPreviewStateProvider : PreviewParameterProvider<RatingPreviewState> {
    override val values = sequenceOf(
        RatingPreviewState(finalPrice = -1.0, liked = false, isStudent = false),
    )
}

@Preview(
    name = "RatingScreen",
    showBackground = true,
    widthDp = 412,
    heightDp = 1800
)
@Composable
private fun RatingScreenPreview(
    @PreviewParameter(RatingPreviewStateProvider::class) state: RatingPreviewState
) {
    GastroVoteTheme(darkTheme = false, dynamicColor = false) {
        RatingScreen(
            bar = "La Taberna del Puerto",
            tapa = Tapa(
                id = 1,
                name = "Marinera murciana",
                description = "Rosquilla crujiente con ensaladilla rusa y una anchoa.",
                price = 3.50
            ),
            totalBars = 8,
            originalPrice = 3.50,
            liked = state.liked,
            isStudent = state.isStudent,
            finalPrice = state.finalPrice,
            selectedSize = TapaSize.TAPA,
            onSizeChange = {},
            rating = 0,
            qualityPrice = 5f,
            onQualityPriceChange = {},
            vegetarian = false,
            onVegetarianChange = {},
            glutenFree = false,
            onGlutenFreeChange = {},
            spicy = false,
            onSpicyChange = {},
            comment = "",
            onCommentChange = {},
            onLikedChange = {},
            onStudentChange = {},
            onPublish = {},
            onClear = {},
            onNextBar = {},
            onPreviousBar = {}
        )
    }
}
