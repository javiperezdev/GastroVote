package es.dam.gastrovote.javiperez.ui.rating.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import es.dam.gastrovote.javiperez.ui.theme.Calypso
import es.dam.gastrovote.javiperez.ui.theme.Calypso35
import es.dam.gastrovote.javiperez.ui.theme.Calypso50
import es.dam.gastrovote.javiperez.ui.theme.Calypso60
import es.dam.gastrovote.javiperez.ui.theme.Calypso75
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue20
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue40
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue45

@Composable
internal fun SectionTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.titleSmall,
        color = CornflowerBlue
    )
}

@Composable
internal fun FieldLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = Calypso
    )
}

@Composable
internal fun SummaryLine(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
internal fun gastroCheckboxColors() = CheckboxDefaults.colors(
    checkedColor = CornflowerBlue,
    uncheckedColor = Calypso60,
    checkmarkColor = Color.White
)

@Composable
internal fun gastroSwitchColors() = SwitchDefaults.colors(
    checkedThumbColor = Color.White,
    checkedTrackColor = CornflowerBlue,
    checkedBorderColor = CornflowerBlue,
    checkedIconColor = Color.White,
    uncheckedThumbColor = Color.White,
    uncheckedTrackColor = Calypso35,
    uncheckedBorderColor = Calypso50,
    uncheckedIconColor = Calypso
)

@Composable
internal fun gastroSliderColors() = SliderDefaults.colors(
    thumbColor = CornflowerBlue,
    activeTrackColor = CornflowerBlue,
    activeTickColor = Color.White,
    inactiveTrackColor = CornflowerBlue20,
    inactiveTickColor = CornflowerBlue40
)

@Composable
internal fun gastroOutlinedTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = CornflowerBlue,
    unfocusedBorderColor = CornflowerBlue45,
    cursorColor = CornflowerBlue,
    focusedLabelColor = CornflowerBlue,
    unfocusedLabelColor = Calypso75
)

@Composable
internal fun gastroSegmentedButtonColors() = SegmentedButtonDefaults.colors(
    activeContainerColor = CornflowerBlue,
    activeContentColor = Color.White,
    activeBorderColor = CornflowerBlue,
    inactiveContainerColor = Color.Transparent,
    inactiveContentColor = Calypso,
    inactiveBorderColor = CornflowerBlue45
)
