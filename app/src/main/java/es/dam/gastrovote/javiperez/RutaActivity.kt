package es.dam.gastrovote.javiperez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.dam.gastrovote.javiperez.data.BarRepository
import es.dam.gastrovote.javiperez.ui.ruta.RutaScreen
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme

class RutaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val bars = BarRepository().bars
            GastroVoteTheme() {
                RutaScreen(bars = bars)
            }
        }
    }
}