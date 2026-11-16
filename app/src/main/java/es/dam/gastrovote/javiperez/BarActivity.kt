package es.dam.gastrovote.javiperez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.dam.gastrovote.javiperez.ui.bar.BarScreen
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme

class BarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val barId = intent.getIntExtra("BAR_ID", 0)
            GastroVoteTheme() {
                BarScreen(barId = barId)
            }
        }
    }
}