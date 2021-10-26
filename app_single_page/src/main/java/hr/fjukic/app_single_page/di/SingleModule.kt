package hr.fjukic.app_single_page.di

import hr.fjukic.app_single_page.city.adapter.CityScreenAdapter
import hr.fjukic.app_single_page.city.viewmodel.CityVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val singlePageModule = module {
    factory { CityScreenAdapter() }
    viewModel { CityVM(get(), get(), get()) }
}