package es.dam.gastrovote.javiperez.ui.rating.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.domain.model.TapaSize
import es.dam.gastrovote.javiperez.ui.asString
import es.dam.gastrovote.javiperez.ui.components.GastroTagCheckbox
import es.dam.gastrovote.javiperez.ui.mapRatingToComment
import kotlin.math.roundToInt

@Composable
internal fun ReviewForm(
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
    onRatingChange: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        FieldLabel(text = stringResource(R.string.size))

        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
            TapaSize.entries.forEachIndexed { index, size ->
                SegmentedButton(
                    selected = selectedSize == size,
                    onClick = { onSizeChange(size) },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = TapaSize.entries.size // Buttons in the same row
                    ),
                    colors = gastroSegmentedButtonColors(),
                    label = {
                        Text(
                            text = size.asString(),
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 2, // maximum lines for the text to span
                            textAlign = TextAlign.Center
                        )
                    }
                )
            }
        }

        FieldLabel(text = stringResource(R.string.rating))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            StarRow(
                rating = rating,
                starSize = 32.sp,
                onRatingChange = onRatingChange
            )

            Text(
                text = stringResource(mapRatingToComment(rating)),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            FieldLabel(text = stringResource(R.string.quality_price))

            Text(
                text = "${qualityPrice.roundToInt()}/10",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Slider(
            value = qualityPrice,
            onValueChange = onQualityPriceChange,
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.fillMaxWidth(),
            colors = gastroSliderColors()
        )

        FieldLabel(text = stringResource(R.string.tags))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            GastroTagCheckbox(
                label = stringResource(R.string.tag_vegetarian),
                checked = vegetarian,
                onCheckedChange = onVegetarianChange,
                modifier = Modifier.weight(1f)
            )
            GastroTagCheckbox(
                label = stringResource(R.string.tag_gluten_free),
                checked = glutenFree,
                onCheckedChange = onGlutenFreeChange,
                modifier = Modifier.weight(1f)
            )
            GastroTagCheckbox(
                label = stringResource(R.string.tag_spicy),
                checked = spicy,
                onCheckedChange = onSpicyChange,
                modifier = Modifier.weight(1f)
            )
        }

        OutlinedTextField(
            value = comment,
            onValueChange = { onCommentChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.comment)) },
            placeholder = { Text(text = stringResource(R.string.comment_hint)) },
            supportingText = {
                Text(
                    text = "${comment.length}/200"
                )
            },
            minLines = 3,
            maxLines = 5,
            shape = RoundedCornerShape(12.dp),
            colors = gastroOutlinedTextFieldColors()
        )
    }
}

