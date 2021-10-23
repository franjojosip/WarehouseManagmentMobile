package hr.fjukic.app_common.repository.home

import hr.fjukic.app_common.model.request.TokenRequest
import hr.fjukic.app_common.model.response.Home
import hr.fjukic.app_common.model.response.MainApiResponse
import hr.fjukic.app_common.restinterface.ApiRestInterface
import hr.fjukic.app_common.sharedpref.AppSharedPreference
import io.reactivex.rxjava3.core.Observable

class HomeRepositoryImpl(
    private val sharedPref: AppSharedPreference,
    private val apiRestInterface: ApiRestInterface
) : HomeRepository {
    override fun getData(): Observable<MainApiResponse<Home>> {
        return apiRestInterface.getHomeData(
            TokenRequest(
                sharedPref.authToken,
                sharedPref.refreshToken
            )
        )
    }
}