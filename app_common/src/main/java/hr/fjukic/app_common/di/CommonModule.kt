package hr.fjukic.app_common.di

import hr.fjukic.app_common.repository.HomeRepository
import hr.fjukic.app_common.repository.HomeRepositoryImpl
import hr.fjukic.app_common.repository.resource.ResourceRepository
import hr.fjukic.app_common.repository.resource.ResourceRepositoryImpl
import hr.fjukic.app_common.repository.user.UserRepository
import hr.fjukic.app_common.repository.user.UserRepositoryImpl
import hr.fjukic.app_common.restinterface.ApiRestInterface
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val commonModule = module {
    single<ApiRestInterface> { get<Retrofit>().create(ApiRestInterface::class.java) }
    single<ResourceRepository> { ResourceRepositoryImpl(androidContext()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get(), get()) }
}