package com.example.gastrovote

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gastrovote.domain.calculate
import com.example.gastrovote.ui.components.GastroVoteButton
import com.example.gastrovote.ui.components.GastroVoteTextField
import com.example.gastrovote.ui.theme.Calypso
import com.example.gastrovote.ui.theme.CornflowerBlue
import com.example.gastrovote.ui.theme.GastroVoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GastroVoteTheme {
                    RatingScreen()

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingScreen(modifier: Modifier = Modifier) {
    var bar by rememberSaveable { mutableStateOf("") }
    var tapa by rememberSaveable { mutableStateOf("") }
    var priceText by rememberSaveable { mutableStateOf("") }
    var finalPrice by rememberSaveable { mutableStateOf(-1.0) }
    var liked by rememberSaveable { mutableStateOf(false) }
    var isStudent by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.gastro_vote_banner),
            null,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp).safeDrawingPadding(),
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
                onValueChange = {
                    bar = it
                    finalPrice = -1.0
                },
                placeholder = stringResource(R.string.bar_name),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            GastroVoteTextField(
                value = tapa,
                onValueChange = {
                    tapa = it
                    finalPrice = -1.0
                },
                placeholder = stringResource(R.string.tapa_name),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            GastroVoteTextField(
                value = priceText,
                onValueChange = {
                    priceText = filterPriceInput(it)
                    finalPrice = -1.0
                },
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
                    onCheckedChange = { liked = it }
                )

                Text(
                    text = stringResource(if (liked) R.string.you_like_it else R.string.did_you_like_it),
                    fontSize = 12.sp,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = stringResource(R.string.student_discount),
                    fontSize = 12.sp
                )
                Checkbox(
                    checked = isStudent,
                    onCheckedChange = { checked ->
                        isStudent = checked
                        finalPrice = -1.0
                        if (checked) {
                            showToast(text = "Recuerda ensenyar tu carnet de estudiante", context = context)
                        }
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                GastroVoteButton(
                    onClick = {
                        bar = ""
                        tapa = ""
                        priceText = ""
                        finalPrice = -1.0
                        liked = false
                        isStudent = false
                    },
                    buttonContent = stringResource(R.string.clean),
                    color = Calypso
                )

                GastroVoteButton(
                    onClick = {
                        val result = calculateFinalPrice(
                            bar = bar,
                            tapa = tapa,
                            priceText = priceText,
                            isStudent = isStudent,
                            context = context
                        )
                        finalPrice = result
                        if (result != -1.0) {
                            showToast(text = "Precio calculado", context = context)
                        }
                    },
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

            ShowTotal(
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

private fun calculateFinalPrice(
    bar: String,
    isStudent: Boolean,
    tapa: String,
    priceText: String,
    context: Context
): Double {
    val price = priceText.replace(',', '.').toDoubleOrNull()

    return when {
        bar.isBlank() || tapa.isBlank() -> {
            showToast(text = "Indica el bar y la tapa", context = context)
            -1.0
        }
        price == null || price <= 0 -> {
            showToast(text = "El precio no es valido", context = context)
            -1.0
        }
        else -> calculate(basePrice = price, isStudent = isStudent)
    }
}

private fun filterPriceInput(input: String): String {
    var separatorFound = false
    return buildString {
        input.forEach { character ->
            when {
                character.isDigit() -> append(character)
                (character == '.' || character == ',') && !separatorFound -> {
                    separatorFound = true
                    append(character)
                }
            }
        }
    }
}

@Composable
private fun ShowTotal(
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

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "Tapa: $tapa",
            fontSize = 12.sp
        )

        Text(
            text = "Bar: $bar",
            fontSize = 12.sp
        )

        Text(
            text = "Precio: $priceText",
            fontSize = 12.sp
        )

        Text(
            text = "Descuento: $discount",
            fontSize = 12.sp
        )

        Text(
            text = "Precio Final $finalPrice",
            fontSize = 12.sp
        )

        Text(
            text = "Me gusta : $like",
            fontSize = 12.sp
        )
    }
}

private fun showToast(text: String, context: Context) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()
}
