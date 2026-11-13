package es.dam.gastrovote.javiperez


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.dam.gastrovote.javiperez.data.BarRepository
import es.dam.gastrovote.javiperez.ui.home.HomeScreen
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val totalBars = BarRepository().bars.size
        setContent {
            GastroVoteTheme {
                HomeScreen(
                    username = "Javi", // TODO(#31): read from UserPreferences
                    reviewedCount = 0, // TODO(#31): read from UserPreferences
                    totalBars = totalBars,
                    onSeeRuta = { /* TODO(#24): open RouteActivity */ }
                )
            }
        }
    }
}
