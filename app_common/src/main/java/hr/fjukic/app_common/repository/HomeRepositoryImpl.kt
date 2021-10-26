package hr.fjukic.app_common.repository

import hr.fjukic.app_common.model.request.TokenRequest
import hr.fjukic.app_common.model.response.Home
import hr.fjukic.app_common.model.response.MainApiResponse
import hr.fjukic.app_common.restinterface.ApiRestInterface
import hr.fjukic.app_common.sharedpref.AppSharedPreference
import io.reactivex.rxjava3.core.Observable

interface HomeRepository {
    fun getData(): Observable<Home>
}

class HomeRepositoryImpl(
    private val sharedPref: AppSharedPreference,
    private val apiRestInterface: ApiRestInterface
) : HomeRepository {
    override fun getData(): Observable<Home> {
        return apiRestInterface.getHomeData(
            TokenRequest(
                sharedPref.authToken,
                sharedPref.refreshToken
            )
        ).map { it.data }
    }
}