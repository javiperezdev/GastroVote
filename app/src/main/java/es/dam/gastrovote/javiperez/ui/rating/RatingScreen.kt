package es.dam.gastrovote.javiperez.ui.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.domain.model.Tapa
import es.dam.gastrovote.javiperez.domain.model.TapaSize
import es.dam.gastrovote.javiperez.ui.rating.components.BarSelector
import es.dam.gastrovote.javiperez.ui.rating.components.LikeSwitchRow
import es.dam.gastrovote.javiperez.ui.rating.components.RatingActionButtons
import es.dam.gastrovote.javiperez.ui.rating.components.RatingHeader
import es.dam.gastrovote.javiperez.ui.rating.components.RatingSummaryCard
import es.dam.gastrovote.javiperez.ui.rating.components.ReviewForm
import es.dam.gastrovote.javiperez.ui.rating.components.RouteProgress
import es.dam.gastrovote.javiperez.ui.rating.components.SectionTitle
import es.dam.gastrovote.javiperez.ui.rating.components.StudentDiscountRow
import es.dam.gastrovote.javiperez.ui.rating.components.TapaInfoCard
import kotlin.math.roundToInt

@Composable
internal fun RatingScreen(
    bar: String,
    tapa: Tapa,
    totalBars: Int,
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
    onNextBar: () -> Unit,
    onPreviousBar: () -> Unit,
    onFavouriteChange: () -> Unit,
    isFavourite: Boolean,
    onRatingChange: (Int) -> Unit,
    ratingComment: String,
    modifier: Modifier = Modifier,
    reviewedBars: Set<Int>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(R.drawable.gastro_vote_banner),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RatingHeader()

            BarSelector(
                bar = bar,
                position = tapa.id,
                total = totalBars,
                onNextBar = onNextBar,
                onPreviousBar = onPreviousBar
            )

            TapaInfoCard(tapa = tapa, isFavourite = isFavourite, onFavouriteChange = onFavouriteChange)

            SectionTitle(text = stringResource(R.string.your_review))

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

            SectionTitle(text = stringResource(R.string.summary_and_progress))

            RouteProgress(reviewedBars = reviewedBars.size, totalBars = totalBars)

            if (finalPrice == -1.0) {
                Text(
                    text = stringResource(R.string.resume),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

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
}
