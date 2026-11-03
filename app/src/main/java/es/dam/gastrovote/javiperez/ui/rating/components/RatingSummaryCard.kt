package es.dam.gastrovote.javiperez.ui.rating.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue08

@Composable
internal fun RatingSummaryCard(
    bar: String,
    tapa: String,
    originalPrice: Double,
    finalPrice: Double,
    liked: Boolean,
    isStudent: Boolean,
    size: String,
    rating: Int,
    qualityPrice: Int,
    vegetarian: Boolean,
    glutenFree: Boolean,
    spicy: Boolean,
    comment: String
) {
    if (finalPrice == -1.0) return

    val locale = LocalConfiguration.current.locales[0]
    val discount = stringResource(
        if (isStudent) R.string.discount_with_student else R.string.discount_without_student
    )
    val like = stringResource(if (liked) R.string.yes else R.string.no)
    val tags = listOfNotNull(
        if (vegetarian) stringResource(R.string.tag_vegetarian) else null,
        if (glutenFree) stringResource(R.string.tag_gluten_free) else null,
        if (spicy) stringResource(R.string.tag_spicy) else null
    ).ifEmpty {
        listOf(stringResource(R.string.none))
    }.joinToString(", ")
    val commentText = if (comment.isBlank()) stringResource(R.string.none) else comment
    val price = stringResource(R.string.price_format, "%.2f".format(locale, originalPrice))
    val finalPriceText = stringResource(
        R.string.price_format,
        "%.2f".format(locale, finalPrice)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = CornflowerBlue08,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SummaryLine("${stringResource(R.string.summary_bar)} $bar")
        SummaryLine("${stringResource(R.string.summary_tapa)} $tapa")
        SummaryLine("${stringResource(R.string.summary_price)} $price")
        SummaryLine("${stringResource(R.string.summary_size)} $size")
        SummaryLine("${stringResource(R.string.summary_quality_price)} $qualityPrice/10")
        SummaryLine("${stringResource(R.string.summary_discount)} $discount")
        SummaryLine("${stringResource(R.string.summary_final_price)} $finalPriceText")
        SummaryLine("${stringResource(R.string.summary_liked)} $like")
        SummaryLine("${stringResource(R.string.summary_tags)} $tags")
        SummaryLine("${stringResource(R.string.summary_comment)} $commentText")
    }
}
