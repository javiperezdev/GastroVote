package es.dam.gastrovote.javiperez.ui.review

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.dam.gastrovote.javiperez.domain.model.Tapa
import es.dam.gastrovote.javiperez.domain.model.TapaSize
import es.dam.gastrovote.javiperez.ui.components.GastroScaffold
import es.dam.gastrovote.javiperez.ui.review.components.LikeSwitchRow
import es.dam.gastrovote.javiperez.ui.review.components.RatingActionButtons
import es.dam.gastrovote.javiperez.ui.review.components.RatingSummaryCard
import es.dam.gastrovote.javiperez.ui.review.components.ReviewForm
import es.dam.gastrovote.javiperez.ui.review.components.StudentDiscountRow
import kotlin.math.roundToInt

@Composable
internal fun ReviewScreen(
    bar: String,
    tapa: Tapa,
    originalPrice: Double,
    liked: Boolean,
    isStudent: Boolean,
    finalPrice: Double,
    selectedSize: TapaSize,
    onSizeChange: (TapaSize) -> Unit,
    rating: Int,
    qualityPrice: Float,
    onQualityPriceChange: (Float) -> Unit,
    vegetarian: Boolean,
    onVegetarianChange: (Boolean) -> Unit,
    glutenFree: Boolean,
    onGlutenFreeChange: (Boolean) -> Unit,
    spicy: Boolean,
    onSpicyChange: (Boolean) -> Unit,
    comment: String,
    onCommentChange: (String) -> Unit,
    onLikedChange: (Boolean) -> Unit,
    onStudentChange: (Boolean) -> Unit,
    onPublish: () -> Unit,
    showClearDialog: Boolean,
    onClearRequest: () -> Unit,
    onClearConfirm: () -> Unit,
    onClearDismiss: () -> Unit,
    onRatingChange: (Int) -> Unit,
    ratingComment: String,
    modifier: Modifier = Modifier,
) {
    GastroScaffold(
        modifier = modifier,
        scrollable = true,
        showBanner = false
    ) {

        ReviewForm(
            selectedSize = selectedSize,
            onSizeChange = onSizeChange,
            rating = rating,
            qualityPrice = qualityPrice,
            onQualityPriceChange = onQualityPriceChange,
            vegetarian = vegetarian,
            onVegetarianChange = onVegetarianChange,
            glutenFree = glutenFree,
            onGlutenFreeChange = onGlutenFreeChange,
            spicy = spicy,
            onSpicyChange = onSpicyChange,
            comment = comment,
            onCommentChange = onCommentChange,
            onRatingChange = onRatingChange
        )

        LikeSwitchRow(liked = liked, onLikedChange = onLikedChange)

        StudentDiscountRow(isStudent = isStudent, onStudentChange = onStudentChange)

        RatingActionButtons(
            showClearDialog = showClearDialog,
            onClearRequest = onClearRequest,
            onClearConfirm = onClearConfirm,
            onClearDismiss = onClearDismiss,
            onPublish = onPublish
        )

        RatingSummaryCard(
            bar = bar,
            tapa = tapa.name,
            originalPrice = originalPrice,
            liked = liked,
            finalPrice = finalPrice,
            isStudent = isStudent,
            size = selectedSize,
            qualityPrice = qualityPrice.roundToInt(),
            vegetarian = vegetarian,
            glutenFree = glutenFree,
            spicy = spicy,
            comment = comment,
            rating = rating,
            ratingComment = ratingComment
        )
    }
}
