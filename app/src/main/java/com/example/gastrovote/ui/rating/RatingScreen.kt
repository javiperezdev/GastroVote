package com.example.gastrovote.ui.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gastrovote.R
import com.example.gastrovote.ui.components.GastroVoteButton
import com.example.gastrovote.ui.components.GastroVoteTextField
import com.example.gastrovote.ui.theme.Calypso
import com.example.gastrovote.ui.theme.CornflowerBlue
import com.example.gastrovote.ui.theme.GastroVoteTheme

@Composable
internal fun RatingScreen(
    bar: String,
    tapa: String,
    priceText: String,
    liked: Boolean,
    isStudent: Boolean,
    finalPrice: Double,
    onBarChange: (String) -> Unit,
    onTapaChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onLikedChange: (Boolean) -> Unit,
    onStudentChange: (Boolean) -> Unit,
    onPublish: () -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
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
                .padding(start = 24.dp, end = 24.dp)
                .safeDrawingPadding(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(R.string.rate_it),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = Calypso,
                fontWeight = FontWeight.Bold
            )

            GastroVoteTextField(
                value = bar,
                onValueChange = onBarChange,
                placeholder = stringResource(R.string.bar_name),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            GastroVoteTextField(
                value = tapa,
                onValueChange = onTapaChange,
                placeholder = stringResource(R.string.tapa_name),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            GastroVoteTextField(
                value = priceText,
                onValueChange = onPriceChange,
                placeholder = stringResource(R.string.tapa_price),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )

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
                tapa = tapa,
                priceText = priceText,
                liked = liked,
                finalPrice = finalPrice,
                isStudent = isStudent
            )
        }
    }
}

@Composable
private fun RatingSummary(
    bar: String,
    tapa: String,
    priceText: String,
    finalPrice: Double,
    liked: Boolean,
    isStudent: Boolean
) {
    if (finalPrice == -1.0) return

    val discount = if (isStudent) "10%" else "0%"
    val like = if (liked) "Si" else "No"

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = "Tapa: $tapa", fontSize = 12.sp)
        Text(text = "Bar: $bar", fontSize = 12.sp)
        Text(text = "Precio: $priceText", fontSize = 12.sp)
        Text(text = "Descuento: $discount", fontSize = 12.sp)
        Text(text = "Precio Final $finalPrice", fontSize = 12.sp)
        Text(text = "Me gusta : $like", fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingScreenPreview() {
    GastroVoteTheme {
        RatingScreen(
            bar = "Bar Pepe",
            tapa = "Patatas bravas",
            priceText = "8,50",
            liked = true,
            isStudent = true,
            finalPrice = 7.65,
            onBarChange = {},
            onTapaChange = {},
            onPriceChange = {},
            onLikedChange = {},
            onStudentChange = {},
            onPublish = {},
            onClear = {}
        )
    }
}
