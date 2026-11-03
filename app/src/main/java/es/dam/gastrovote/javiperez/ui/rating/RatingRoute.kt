package es.dam.gastrovote.javiperez.ui.rating

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.data.BarRepository
import es.dam.gastrovote.javiperez.domain.calculate

@Composable
fun RatingRoute(modifier: Modifier = Modifier) {
    var currentBarIndex by rememberSaveable { mutableIntStateOf(0) }
    val bars = remember { BarRepository().bars }
    val bar = bars[currentBarIndex]
    val tapa = bar.tapa
    val originalPrice = tapa.price

    var finalPrice by rememberSaveable { mutableDoubleStateOf(-1.0) }
    var liked by rememberSaveable { mutableStateOf(false) }
    var isStudent by rememberSaveable { mutableStateOf(false) }

    var selectedSize by rememberSaveable(bar.id) { mutableStateOf(TapaSize.TAPA) }
    var rating by rememberSaveable(bar.id) { mutableIntStateOf(0) }
    var qualityPrice by rememberSaveable(bar.id) {
        mutableFloatStateOf(5f)
    }
    var vegetarian by rememberSaveable(bar.id) { mutableStateOf(false) }
    var glutenFree by rememberSaveable(bar.id) { mutableStateOf(false) }
    var spicy by rememberSaveable(bar.id) { mutableStateOf(false) }
    var comment by rememberSaveable(bar.id) { mutableStateOf("") }

    val context = LocalContext.current

    val studentReminder = stringResource(R.string.student_card_reminder)
    val priceCalculated = stringResource(R.string.price_calculated)

    RatingScreen(
        bar = bar.name,
        totalBars = bars.size,
        tapa = tapa,
        originalPrice = originalPrice,
        liked = liked,
        isStudent = isStudent,
        finalPrice = finalPrice,
        selectedSize = selectedSize,
        onSizeChange = { selectedSize = it },
        rating = rating,
        qualityPrice = qualityPrice,
        onQualityPriceChange = { qualityPrice = it },
        vegetarian = vegetarian,
        onVegetarianChange = { vegetarian = it },
        glutenFree = glutenFree,
        onGlutenFreeChange = { glutenFree = it },
        spicy = spicy,
        onSpicyChange = { spicy = it },
        comment = comment,
        onCommentChange = { comment = it },
        onLikedChange = { liked = it },
        onStudentChange = { checked ->
            isStudent = checked
            finalPrice = -1.0
            if (checked) {
                showToast(
                    text = studentReminder,
                    context = context
                )
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
                showToast(
                    text = priceCalculated,
                    context = context
                )
            }
        },
        onClear = {
            finalPrice = -1.0
            liked = false
            isStudent = false
            selectedSize = TapaSize.TAPA
            rating = 0
            qualityPrice = 5f
            vegetarian = false
            glutenFree = false
            spicy = false
            comment = ""
        },
        onNextBar = {
            currentBarIndex = (currentBarIndex + 1) % bars.size
        },
        onPreviousBar = {
            currentBarIndex = (currentBarIndex - 1 + bars.size) % bars.size
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
