package com.bimo0064.assesment1_miniproject

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import java.util.*

class LocaleContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {
        fun wrap(context: Context, language: String): ContextWrapper {
            val newLocale = Locale(language)
            Locale.setDefault(newLocale)

            val config = Configuration(context.resources.configuration)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(newLocale)
                config.setLayoutDirection(newLocale)
            } else {
                config.locale = newLocale
            }

            return LocaleContextWrapper(context.createConfigurationContext(config))
        }
    }
}
