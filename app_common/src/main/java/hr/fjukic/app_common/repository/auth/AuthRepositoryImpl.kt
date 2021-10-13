package hr.fjukic.app_common.repository.auth

import hr.fjukic.app_common.model.request.LoginRequest
import hr.fjukic.app_common.model.response.UserApiResponse
import hr.fjukic.app_common.restinterface.ApiRestInterface
import hr.fjukic.app_common.sharedpref.AppSharedPreference
import io.reactivex.rxjava3.core.Observable

class AuthRepositoryImpl(
    private val sharedPref: AppSharedPreference,
    private val apiRestInterface: ApiRestInterface
) : AuthRepository {
    override fun login(loginRequest: LoginRequest): Observable<UserApiResponse> {
        return apiRestInterface.login(loginRequest).doOnNext {
            sharedPref.user = it.user
        }
    }
}