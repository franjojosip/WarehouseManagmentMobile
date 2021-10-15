package hr.fjukic.warehousemanagment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hr.fjukic.app_common.repository.resource.ResourceRepository
import hr.fjukic.app_common.repository.resource.ResourceRepositoryImpl
import hr.fjukic.app_common.sharedpref.AppSharedPreference
import hr.fjukic.warehousemanagment.localization.LocaleManager
import hr.fjukic.warehousemanagment.network.provideRetrofit
import hr.fjukic.warehousemanagment.sharedpref.AppSharedPreferencesImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<Gson> { GsonBuilder().create() }
    single<AppSharedPreference> { AppSharedPreferencesImpl(get(), get()) }
    single { LocaleManager(get()) }
    single { provideRetrofit() }
}