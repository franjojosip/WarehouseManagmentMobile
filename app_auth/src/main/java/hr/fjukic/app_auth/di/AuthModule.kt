package hr.fjukic.app_auth.di

import hr.fjukic.app_auth.forgotpassword.adapter.ForgotPasswordScreenAdapter
import hr.fjukic.app_auth.forgotpassword.viewmodel.ForgotPasswordVM
import hr.fjukic.app_auth.login.adapter.LoginScreenAdapter
import hr.fjukic.app_auth.login.viewmodel.LoginVM
import hr.fjukic.app_common.repository.auth.AuthRepository
import hr.fjukic.app_common.repository.auth.AuthRepositoryImpl
import org.koin.dsl.module

val authModule = module {
    factory { LoginScreenAdapter() }
    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { LoginVM(get(), get(), get()) }

    factory { ForgotPasswordScreenAdapter() }
    factory { ForgotPasswordVM(get(), get()) }
}