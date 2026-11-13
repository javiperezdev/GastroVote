package es.dam.gastrovote.javiperez


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.dam.gastrovote.javiperez.ui.review.ReviewRoute
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GastroVoteTheme {
                ReviewRoute()
            }
        }
    }
}
