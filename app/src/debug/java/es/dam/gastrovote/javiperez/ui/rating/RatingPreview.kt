package es.dam.gastrovote.javiperez.ui.rating

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import es.dam.gastrovote.javiperez.domain.model.Tapa
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme

@Preview(
    name = "RatingScreen",
    showBackground = true,
    widthDp = 412,
    heightDp = 1800
)
@Composable
private fun RatingScreenPreview() {
    val tapa = Tapa(
        id = 1,
        name = "Marinera murciana",
        description = "Rosquilla crujiente con ensaladilla rusa y una anchoa.",
        price = 3.50,
        rating = 5
    )
    GastroVoteTheme(darkTheme = true) {
        RatingScreen(
            bar = "La Taberna del Puerto",
            tapa = tapa,
            totalBars = 8,
            originalPrice = 3.50,
            liked = false,
            isStudent = false,
            finalPrice = -1.0,
            selectedSize = tapa.size,
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
            showClearDialog = false,
            onClearRequest = {},
            onClearConfirm = {},
            onClearDismiss = {},
            onNextBar = {},
            onPreviousBar = {},
            onFavouriteChange = {},
            isFavourite = false,
            onRatingChange = {},
            ratingComment = "",
            reviewedBars = setOf()
        )
    }
}
