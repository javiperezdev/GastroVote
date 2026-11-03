package es.dam.gastrovote.javiperez.ui.rating.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.ui.components.GastroButton
import es.dam.gastrovote.javiperez.ui.theme.Calypso
import es.dam.gastrovote.javiperez.ui.theme.CornflowerBlue

@Composable
internal fun LikeSwitchRow(
    liked: Boolean,
    onLikedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.i_like_it),
            style = MaterialTheme.typography.bodySmall
        )

        Switch(
            checked = liked,
            onCheckedChange = onLikedChange,
            colors = gastroSwitchColors()
        )

        Text(
            text = stringResource(
                if (liked) R.string.you_like_it else R.string.did_you_like_it
            ),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodySmall,
            color = CornflowerBlue
        )
    }
}

@Composable
internal fun StudentDiscountRow(
    isStudent: Boolean,
    onStudentChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.student_discount),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodySmall
        )
        Checkbox(
            checked = isStudent,
            onCheckedChange = onStudentChange,
            colors = gastroCheckboxColors()
        )
    }
}

@Composable
internal fun RatingActionButtons(
    onClear: () -> Unit,
    onPublish: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GastroButton(
            onClick = onClear,
            buttonContent = stringResource(R.string.clean),
            color = Calypso,
            modifier = Modifier.weight(1f)
        )

        GastroButton(
            onClick = onPublish,
            buttonContent = stringResource(R.string.post_review),
            color = CornflowerBlue,
            modifier = Modifier.weight(1f)
        )
    }
}
