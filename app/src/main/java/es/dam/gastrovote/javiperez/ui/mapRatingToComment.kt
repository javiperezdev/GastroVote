package es.dam.gastrovote.javiperez.ui

import es.dam.gastrovote.javiperez.R


internal fun mapRatingToComment(rating: Int): Int {
    return when (rating) {
        0 -> R.string.rating_none
        1 -> R.string.rating_poor
        2 -> R.string.rating_poor
        3 -> R.string.rating_ok
        4 -> R.string.rating_good
        5 -> R.string.rating_excellent
        else -> R.string.rating_none
    }
}