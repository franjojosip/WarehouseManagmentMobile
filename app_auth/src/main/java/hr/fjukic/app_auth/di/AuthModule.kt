package hr.fjukic.app_auth.di

import hr.fjukic.app_auth.login.viewmodel.LoginVM
import org.koin.dsl.module

val authModule = module {
    factory { LoginVM() }
}