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
import es.dam.gastrovote.javiperez.domain.model.TapaSize


@Composable
fun RatingRoute(modifier: Modifier = Modifier) {
    var currentBarIndex by rememberSaveable { mutableIntStateOf(0) }
    val bars = remember { BarRepository().bars }
    val bar = bars[currentBarIndex]
    val tapa = bar.tapa
    val originalPrice = tapa.price

    var finalPrice by rememberSaveable(bar.id) { mutableDoubleStateOf(-1.0) }
    var liked by rememberSaveable(bar.id) { mutableStateOf(false) }
    var isStudent by rememberSaveable(bar.id) { mutableStateOf(false) }

    var selectedSize by rememberSaveable(bar.id) { mutableStateOf(TapaSize.TAPA) }
    var rating by rememberSaveable(bar.id) { mutableIntStateOf(0) }
    var qualityPrice by rememberSaveable(bar.id) {mutableFloatStateOf(5f) }
    var isBarFavourite by rememberSaveable { mutableStateOf(mapOf<Int, Boolean>()) }

    var vegetarian by rememberSaveable(bar.id) { mutableStateOf(false) }
    var glutenFree by rememberSaveable(bar.id) { mutableStateOf(false) }
    var spicy by rememberSaveable(bar.id) { mutableStateOf(false) }
    var comment by rememberSaveable(bar.id) { mutableStateOf("") }

    val context = LocalContext.current

    val studentReminder = stringResource(R.string.student_card_reminder)
    val priceCalculated = stringResource(R.string.price_calculated)
    val addedFavourite = stringResource(R.string.added_favourite)
    val removedFavourite = stringResource(R.string.removed_favourite)


    val isFavourite = isBarFavourite[bar.id] ?: false

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
        onCommentChange = { comment = it.take(200) },
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
                tapaSize = selectedSize
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
        onFavouriteChange = {
            // Create a new map with the new pair if it doesn't exist
            isBarFavourite = isBarFavourite + (bar.id to !isFavourite)

            val toastText = if (!isFavourite) addedFavourite else removedFavourite
            showToast(text = toastText, context = context)
        },
        isFavourite = isFavourite,
        modifier = modifier
    )
}

private fun calculateFinalPrice(
    isStudent: Boolean,
    originalPrice: Double,
    tapaSize: TapaSize,
): Double {
    return calculate(basePrice = originalPrice, isStudent = isStudent, tapaSize = tapaSize)
}

private fun showToast(text: String, context: Context) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()
}
