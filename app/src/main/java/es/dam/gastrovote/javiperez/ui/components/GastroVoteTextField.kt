package es.dam.gastrovote.javiperez.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import es.dam.gastrovote.javiperez.ui.theme.Calypso

@Composable
internal fun GastroVoteTextField(value: String, placeholder: String, onValueChange: (String) -> Unit, keyboardOptions: KeyboardOptions) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        placeholder = { Text(placeholder) },
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Calypso,
            unfocusedBorderColor = Color.DarkGray,
            focusedLabelColor = Calypso,
            unfocusedLabelColor = Color.DarkGray,
            cursorColor = Calypso
        )
    )
}