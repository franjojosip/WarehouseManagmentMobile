package hr.fjukic.warehousemanagment

import android.app.Application
import android.content.res.Configuration
import hr.fjukic.app_auth.di.authModule
import hr.fjukic.warehousemanagment.di.appModule
import hr.fjukic.warehousemanagment.localization.LocaleManager
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class BaseApp : Application() {
    private val localeManager: LocaleManager by lazy { get() }

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(localeManager.updateConfiguration(newConfig))
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApp)
            modules(provideKoinModules())
        }
    }

    private fun provideKoinModules(): List<Module> {
        return listOf(
            appModule,
            authModule
        )
    }
}