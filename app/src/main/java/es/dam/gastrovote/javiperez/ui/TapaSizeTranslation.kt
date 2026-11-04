package es.dam.gastrovote.javiperez.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import es.dam.gastrovote.javiperez.R
import es.dam.gastrovote.javiperez.domain.model.TapaSize

@Composable
internal fun TapaSize.asString(): String { // Extension function to convert TapaSize to String
    val resId = when (this) {
        TapaSize.TAPA -> R.string.size_tapa
        TapaSize.HALF_PORTION -> R.string.size_half
        TapaSize.PORTION -> R.string.size_full
    }
    return stringResource(resId)
}