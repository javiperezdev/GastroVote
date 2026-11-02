package es.dam.gastrovote.javiperez

import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.dam.gastrovote.javiperez.ui.rating.RatingRoute
import es.dam.gastrovote.javiperez.ui.theme.GastroVoteTheme
import java.util.Locale

private const val DEFAULT_LANGUAGE = "es"

class MainActivity : ComponentActivity() {
    override fun attachBaseContext(newBase: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            super.attachBaseContext(newBase)
        } else {
            val configuration = Configuration(newBase.resources.configuration)
            configuration.setLocale(Locale.forLanguageTag(DEFAULT_LANGUAGE))
            super.attachBaseContext(newBase.createConfigurationContext(configuration))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyDefaultLanguage()
        enableEdgeToEdge()
        setContent {
            GastroVoteTheme {
                RatingRoute()
            }
        }
    }

    private fun applyDefaultLanguage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = getSystemService(LocaleManager::class.java) ?: return
            if (localeManager.applicationLocales.isEmpty) {
                localeManager.applicationLocales = LocaleList.forLanguageTags(DEFAULT_LANGUAGE)
            }
        }
    }
}
