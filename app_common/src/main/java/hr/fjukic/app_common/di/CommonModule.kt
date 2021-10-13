package hr.fjukic.app_common.di

import hr.fjukic.app_common.restinterface.ApiRestInterface
import org.koin.dsl.module
import retrofit2.Retrofit

val commonModule = module {
    single<ApiRestInterface> { get<Retrofit>().create(ApiRestInterface::class.java) }
}