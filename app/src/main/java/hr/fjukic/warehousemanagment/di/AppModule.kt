package hr.fjukic.warehousemanagment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hr.fjukic.app_common.router.AppRouter
import hr.fjukic.app_common.sharedpref.AppSharedPreference
import hr.fjukic.app_common.view.AppFragment
import hr.fjukic.warehousemanagment.localization.LocaleManager
import hr.fjukic.warehousemanagment.navigation.AppNavigationController
import hr.fjukic.app_common.router.NavigationController
import hr.fjukic.warehousemanagment.network.provideRetrofit
import hr.fjukic.warehousemanagment.sharedpref.AppSharedPreferencesImpl
import org.koin.dsl.module

val appModule = module {
    single<Gson> { GsonBuilder().create() }
    single<AppSharedPreference> { AppSharedPreferencesImpl(get(), get()) }
    single { LocaleManager(get()) }
    single { provideRetrofit() }
    factory<NavigationController> { (fragment: AppFragment<*, *>) ->
        AppNavigationController(fragment)
    }

    factory { AppRouter() }
}