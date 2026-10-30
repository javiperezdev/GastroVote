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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    var bar by remember { mutableStateOf("") }
    var tapa by remember { mutableStateOf("") }
    var price by remember { mutableDoubleStateOf(0.0) }
    var finalPrice by remember { mutableDoubleStateOf(-1.0) }
    var liked by remember { mutableStateOf(false) }
    var isStudent by remember { mutableStateOf(false) }

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
            modifier = modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp).safeDrawingPadding(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(R.string.rate_it),
                modifier = modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = Calypso,
                fontWeight = FontWeight.Bold
            )

            GastroVoteTextField(
                value = bar,
                onValueChange = { bar = it },
                placeholder = stringResource(R.string.bar_name),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            GastroVoteTextField(
                value = tapa,
                onValueChange = { tapa = it },
                placeholder = stringResource(R.string.tapa_name),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            GastroVoteTextField(
                value = price.toString(),
                onValueChange = { price = it.toDouble()},
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
                    onCheckedChange = {liked = !liked }
                )

                Text(
                    text = stringResource(if (liked) R.string.you_like_it else R.string.did_you_like_it),
                    fontSize = 12.sp,
                )
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically) {

                if(isStudent) {
                    showToast(text="Recuerda ensenyar tu carnet de estudiante", context = context)
                }

                Text(
                    text = stringResource(R.string.student_discount),
                    fontSize = 12.sp
                )
                Checkbox(
                    checked = isStudent,
                    onCheckedChange = {
                        isStudent = !isStudent
                    }
                )
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                GastroVoteButton(
                    onClick = {
                        bar = ""
                        tapa = ""
                        price = 0.0
                        finalPrice = -1.0
                        liked = false
                        isStudent = false
                    },
                    buttonContent = stringResource(R.string.clean),
                    color = Calypso
                )

                GastroVoteButton(
                    onClick = {
                        finalPrice = calculateFinalPrice(bar = bar, tapa = tapa, price = price, isStudent = isStudent, context = context)
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

            ShowTotal(bar = bar, tapa = tapa, price = price, liked = liked, finalPrice = finalPrice, isStudent = isStudent, context = context)
        }
    }
}

private fun calculateFinalPrice(bar: String, isStudent: Boolean, tapa: String, price: Double, context: Context): Double {
    if (bar == "" || tapa == "") {
        showToast(text="Indica el bar y la tapa", context = context)
    }
    else if (price <= 0) {
        showToast(text="El precio no es valido", context = context)
    }
    else {
        return calculate(basePrice = price, isStudent = isStudent)
    }
    return -1.0
}

@Composable
private fun ShowTotal(bar: String, tapa: String, price: Double, finalPrice: Double, liked: Boolean, isStudent: Boolean, context: Context) {
    if (finalPrice == -1.0) return
    val discount = if (isStudent) "10%" else "0%"
    val like = if(liked) "Si" else "No"

    showToast(text = "Precio calculado", context)

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
            text = "Precio: $price",
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
