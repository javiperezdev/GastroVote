package es.dam.gastrovote.javiperez.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.ui.components.FieldLabel
import es.dam.gastrovote.javiperez.ui.components.GastroScaffold
import es.dam.gastrovote.javiperez.ui.components.GastroSwitch

@Composable
internal fun SettingsScreen() {
    var notificationsEnabled by rememberSaveable { mutableStateOf(true) }

    GastroScaffold(
        showBanner = false,
        scrollable = true
    ) {
        FieldLabel(text = stringResource(R.string.username)) // Name has to be extracted from user preferences
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FieldLabel(
                text = stringResource(
                    if (notificationsEnabled) R.string.turn_off_notifications
                    else R.string.turn_on_notifications
                ),
                modifier = Modifier.weight(1f)
            )
            GastroSwitch(
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )
        }
    }
}