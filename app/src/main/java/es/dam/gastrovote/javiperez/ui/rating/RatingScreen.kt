package es.dam.gastrovote.javiperez.ui.rating

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.domain.model.Tapa
import es.dam.gastrovote.javiperez.ui.components.GastroVoteButton
import es.dam.gastrovote.javiperez.ui.theme.Calypso
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme

@Composable
internal fun RatingScreen(
    bar: String,
    tapa: Tapa,
    originalPrice: Double,
    liked: Boolean,
    isStudent: Boolean,
    finalPrice: Double,
    onLikedChange: (Boolean) -> Unit,
    onStudentChange: (Boolean) -> Unit,
    onPublish: () -> Unit,
    onClear: () -> Unit,
    onNextBar: () -> Unit,
    onPreviousBar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().safeDrawingPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.gastro_vote_banner),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {

            Text(
                text = stringResource(R.string.app_title),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 24.sp,
                color = Calypso,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.app_subtitle),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = CornflowerBlue,
                fontWeight = FontWeight.Bold
            )

            BarSelector(bar = bar, onNextBar = onNextBar, onPreviousBar = onPreviousBar)

            TapaCard(tapa)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(
                    text = stringResource(R.string.i_like_it),
                    fontSize = 12.sp
                )

                Switch(
                    checked = liked,
                    onCheckedChange = onLikedChange
                )

                Text(
                    text = stringResource(if (liked) R.string.you_like_it else R.string.did_you_like_it),
                    fontSize = 12.sp,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.student_discount),
                    fontSize = 12.sp
                )
                Checkbox(
                    checked = isStudent,
                    onCheckedChange = onStudentChange
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GastroVoteButton(
                    onClick = onClear,
                    buttonContent = stringResource(R.string.clean),
                    color = Calypso
                )

                GastroVoteButton(
                    onClick = onPublish,
                    buttonContent = stringResource(R.string.post_review),
                    color = CornflowerBlue
                )
            }

            if (finalPrice == -1.0) {
                Text(
                    text = stringResource(R.string.resume),
                    fontSize = 12.sp
                )
            }

            RatingSummary(
                bar = bar,
                tapa = tapa.name,
                originalPrice = originalPrice,
                liked = liked,
                finalPrice = finalPrice,
                isStudent = isStudent
            )
        }
    }
}

@Composable
private fun TapaCard(tapa: Tapa) {
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = tapa.name)
            Image(painterResource(R.drawable.favourite), null)
        }

        Text(text=tapa.description)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = tapa.price.toString())
            Text(text = "Stars rating here")
        }
    }
}
@Composable
private fun BarSelector(
    bar: String,
    onNextBar: () -> Unit,
    onPreviousBar: () -> Unit
) {
    Column() {
        Text(
            text = stringResource(R.string.choose_bar),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp,
            color = CornflowerBlue,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            FilledTonalButton(
                onClick = onPreviousBar
            ) {
                Image(painter = painterResource(R.drawable.arrow_left), contentDescription = null)
            }

            Text(text = bar, fontSize = 16.sp, color = CornflowerBlue)

            FilledTonalButton(
                onClick = onNextBar
            ) {
                Image(painter = painterResource(R.drawable.arrow_right), contentDescription = null)
            }
        }
    }
}

@Composable
private fun RatingSummary(
    bar: String,
    tapa: String,
    originalPrice: Double,
    finalPrice: Double,
    liked: Boolean,
    isStudent: Boolean
) {
    if (finalPrice == -1.0) return

    val discount = stringResource(
        if (isStudent) R.string.discount_with_student else R.string.discount_without_student
    )
    val like = stringResource(if (liked) R.string.yes else R.string.no)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = stringResource(R.string.summary_tapa, tapa), fontSize = 12.sp)
        Text(text = stringResource(R.string.summary_bar, bar), fontSize = 12.sp)
        Text(text = stringResource(R.string.summary_price, originalPrice.toString()), fontSize = 12.sp)
        Text(text = stringResource(R.string.summary_discount, discount), fontSize = 12.sp)
        Text(text = stringResource(R.string.summary_final_price, finalPrice), fontSize = 12.sp)
        Text(text = stringResource(R.string.summary_liked, like), fontSize = 12.sp)
    }
}
