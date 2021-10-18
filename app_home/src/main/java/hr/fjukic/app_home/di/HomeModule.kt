package hr.fjukic.app_home.di

import hr.fjukic.app_home.container.adapter.MainScreenContainerScreenAdapter
import hr.fjukic.app_home.container.viewmodel.MainScreenContainerVM
import hr.fjukic.app_home.home.adapter.HomeScreenAdapter
import hr.fjukic.app_home.home.viewmodel.HomeVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { MainScreenContainerScreenAdapter() }
    viewModel { MainScreenContainerVM(get(), get()) }

    factory { HomeScreenAdapter() }
    viewModel { HomeVM(get(), get(), get(), get()) }
}
