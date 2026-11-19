package es.dam.gastrovote.javiperez.ui.review

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.domain.calculatePrice
import es.dam.gastrovote.javiperez.domain.model.Bar
import es.dam.gastrovote.javiperez.domain.model.TapaSize
import es.dam.gastrovote.javiperez.ui.mapRatingToComment


@Composable
fun ReviewRoute(
    bar: Bar,
    modifier: Modifier = Modifier
) {

    val activity = LocalActivity.current
    val tapa = bar.tapa
    val originalPrice = tapa.price

    var finalPrice by rememberSaveable(bar.id) { mutableDoubleStateOf(-1.0) }
    var liked by rememberSaveable(bar.id) { mutableStateOf(false) }
    var isStudent by rememberSaveable(bar.id) { mutableStateOf(false) }

    var selectedSize by rememberSaveable(bar.id) { mutableStateOf(TapaSize.TAPA) }
    var rating by rememberSaveable(bar.id) { mutableIntStateOf(0) }
    var qualityPrice by rememberSaveable(bar.id) {mutableFloatStateOf(5f) }

    var vegetarian by rememberSaveable(bar.id) { mutableStateOf(false) }
    var glutenFree by rememberSaveable(bar.id) { mutableStateOf(false) }
    var spicy by rememberSaveable(bar.id) { mutableStateOf(false) }
    var comment by rememberSaveable(bar.id) { mutableStateOf("") }

    var showClearDialog by rememberSaveable { mutableStateOf(false) }
    var showDiscardDialog by rememberSaveable { mutableStateOf(false) }

    val hasUnsavedData = rating != 0 || comment.isNotBlank() || liked || isStudent ||
        qualityPrice != 5f || selectedSize != TapaSize.TAPA ||
        vegetarian || glutenFree || spicy

    BackHandler(enabled = hasUnsavedData) { showDiscardDialog = true }

    val context = LocalContext.current

    val studentReminder = stringResource(R.string.student_card_reminder)
    val publishReview = stringResource(R.string.review_pulbished)
    val rateTapa = stringResource(R.string.rate_tapa)
    val formCleared = stringResource(R.string.form_cleared)


    ReviewScreen(
        bar = bar.name,
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
            if (rating == 0) {
                showToast(
                    text = rateTapa,
                    context = context
                )
            }

            else {
                val result = calculateFinalPrice(
                    originalPrice = originalPrice,
                    isStudent = isStudent,
                    tapaSize = selectedSize
                )

                finalPrice = result

                val resultIntent = Intent()
                resultIntent.putExtra("tapaRating", rating)
                resultIntent.putExtra("tapaSize", selectedSize)
                resultIntent.putExtra("finalPrice", result)

                activity?.setResult(
                    Activity.RESULT_OK,
                    resultIntent
                )

                showToast(
                    text = publishReview,
                    context = context
                )

                activity?.finish()
            }
        },
        showClearDialog = showClearDialog,
        onClearRequest = { showClearDialog = true },
        onClearConfirm = {
            showClearDialog = false
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

            showToast(text = formCleared, context = context)
        },
        onClearDismiss = { showClearDialog = false },
        onRatingChange = {
            newRating -> rating = newRating
        },
        ratingComment = stringResource(mapRatingToComment(rating)),
        modifier = modifier
    )

    if (showDiscardDialog) {
        AlertDialog(
            onDismissRequest = { showDiscardDialog = false },
            title = { Text(stringResource(R.string.discard_confirm_title)) },
            text = { Text(stringResource(R.string.discard_confirm_message)) },
            confirmButton = {
                TextButton(onClick = {
                    showDiscardDialog = false
                    activity?.finish()
                }) {
                    Text(stringResource(R.string.discard_confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDiscardDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}

private fun calculateFinalPrice(
    isStudent: Boolean,
    originalPrice: Double,
    tapaSize: TapaSize,
): Double {
    return calculatePrice(basePrice = originalPrice, isStudent = isStudent, tapaSize = tapaSize)
}

private fun showToast(text: String, context: Context) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()
}
