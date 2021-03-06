package hr.fjukic.app_auth.di

import hr.fjukic.app_auth.forgotpassword.adapter.ForgotPasswordScreenAdapter
import hr.fjukic.app_auth.forgotpassword.viewmodel.ForgotPasswordVM
import hr.fjukic.app_auth.login.adapter.LoginScreenAdapter
import hr.fjukic.app_auth.login.viewmodel.LoginVM
import hr.fjukic.app_common.repository.AuthRepository
import hr.fjukic.app_common.repository.AuthRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    factory { LoginScreenAdapter() }
    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    viewModel { LoginVM(get(), get(), get(), get(), get(), get()) }

    factory { ForgotPasswordScreenAdapter() }
    viewModel { ForgotPasswordVM(get(), get(), get(), get(), get()) }
}