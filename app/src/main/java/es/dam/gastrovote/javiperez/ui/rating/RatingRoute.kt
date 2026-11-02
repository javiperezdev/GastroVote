package es.dam.gastrovote.javiperez.ui.rating

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.data.BarRepository
import es.dam.gastrovote.javiperez.domain.calculate

@Composable
fun RatingRoute(modifier: Modifier = Modifier) {
    var currentBarIndex by rememberSaveable { mutableIntStateOf(0)}
    val bars = BarRepository().bars
    var bar = bars[currentBarIndex]
    var tapa = bar.tapa
    val originalPrice = tapa.price
    var finalPrice by rememberSaveable { mutableDoubleStateOf(-1.0) }
    var liked by rememberSaveable { mutableStateOf(false) }
    var isStudent by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    RatingScreen(
        bar = bar.name,
        tapa = tapa,
        originalPrice = originalPrice,
        liked = liked,
        isStudent = isStudent,
        finalPrice = finalPrice,
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
                originalPrice = originalPrice,
                isStudent = isStudent,
                context = context
            )
            finalPrice = result
            if (result != -1.0) {
                showToast(text = context.getString(R.string.price_calculated), context = context)
            }
        },
        onClear = {
            finalPrice = -1.0
            liked = false
            isStudent = false
        },
        onNextBar = {
            currentBarIndex += 1
            if (currentBarIndex >= bars.size) {
                currentBarIndex = 0
            }
            bar = bars[currentBarIndex]
        },
        onPreviousBar = {
            currentBarIndex -= 1
            if (currentBarIndex < 0) {
                currentBarIndex = bars.size - 1
            }
            bar = bars[currentBarIndex]
        },
        modifier = modifier
    )
}

private fun calculateFinalPrice(
    isStudent: Boolean,
    originalPrice: Double,
    context: Context
): Double {
    if (originalPrice <= 0) {
        showToast(text = context.getString(R.string.error_invalid_price), context = context)
        return -1.0
    } else {
        return calculate(basePrice = originalPrice, isStudent = isStudent)
    }
}


private fun showToast(text: String, context: Context) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()
}
