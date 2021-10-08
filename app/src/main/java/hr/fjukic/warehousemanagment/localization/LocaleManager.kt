package hr.fjukic.warehousemanagment.localization

import android.content.Context
import android.content.res.Configuration
import hr.fjukic.app_common.sharedpref.AppSharedPreference
import java.util.*

open class LocaleManager(private val appSharedPreference: AppSharedPreference) {

    open fun updateContextConfiguration(context: Context?) =
        setContextLocale(context, appSharedPreference.appLocale)

    open fun updateConfiguration(configuration: Configuration): Configuration {
        val locale = Locale(appSharedPreference.appLocale)
        return updateConfigLocale(configuration, locale)
    }

    companion object {
        fun setContextLocale(context: Context?, locale: String): Context? {
            val config = context?.resources?.configuration ?: return context
            val locale = Locale(locale)
            val configuration = updateConfigLocale(config, locale)
            return context.createConfigurationContext(configuration)
        }

        fun updateConfigLocale(configuration: Configuration, locale: Locale): Configuration {
            val overrideConfiguration = Configuration(configuration)
            Locale.setDefault(locale)
            overrideConfiguration.setLocale(locale)
            overrideConfiguration.setLayoutDirection(locale)
            return overrideConfiguration
        }
    }
}