package hr.fjukic.warehousemanagment.di

import hr.fjukic.app_common.sharedpref.AppSharedPreference
import hr.fjukic.warehousemanagment.localization.LocaleManager
import hr.fjukic.warehousemanagment.sharedpref.AppSharedPreferencesImpl
import org.koin.dsl.module

val appModule = module {
    single<AppSharedPreference> { AppSharedPreferencesImpl(get()) }
    single { LocaleManager(get<AppSharedPreference>()) }
}