package es.dam.gastrovote.javiperez.ui.rating

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.domain.calculate

@Composable
fun RatingRoute(modifier: Modifier = Modifier) {
    var bar by rememberSaveable { mutableStateOf("") }
    var tapa by rememberSaveable { mutableStateOf("") }
    var priceText by rememberSaveable { mutableStateOf("") }
    var finalPrice by rememberSaveable { mutableStateOf(-1.0) }
    var liked by rememberSaveable { mutableStateOf(false) }
    var isStudent by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    RatingScreen(
        bar = bar,
        tapa = tapa,
        priceText = priceText,
        liked = liked,
        isStudent = isStudent,
        finalPrice = finalPrice,
        onBarChange = {
            bar = it
            finalPrice = -1.0
        },
        onTapaChange = {
            tapa = it
            finalPrice = -1.0
        },
        onPriceChange = {
            priceText = filterPriceInput(it)
            finalPrice = -1.0
        },
        onLikedChange = { liked = it },
        onStudentChange = { checked ->
            isStudent = checked
            finalPrice = -1.0
            if (checked) {
                showToast(text = context.getString(R.string.student_card_reminder), context = context)
            }
        },
        onPublish = {
            val result = calculateFinalPrice(
                bar = bar,
                tapa = tapa,
                priceText = priceText,
                isStudent = isStudent,
                context = context
            )
            finalPrice = result
            if (result != -1.0) {
                showToast(text = context.getString(R.string.price_calculated), context = context)
            }
        },
        onClear = {
            bar = ""
            tapa = ""
            priceText = ""
            finalPrice = -1.0
            liked = false
            isStudent = false
        },
        modifier = modifier
    )
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
            showToast(text = context.getString(R.string.error_bar_and_tapa), context = context)
            -1.0
        }
        price == null || price <= 0 -> {
            showToast(text = context.getString(R.string.error_invalid_price), context = context)
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

private fun showToast(text: String, context: Context) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()
}
