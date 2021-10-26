package hr.fjukic.app_common.repository

import hr.fjukic.app_common.model.request.LoginRequest
import hr.fjukic.app_common.model.request.ResetPasswordRequest
import hr.fjukic.app_common.model.response.ApiResponse
import hr.fjukic.app_common.model.response.UserApiResponse
import hr.fjukic.app_common.restinterface.ApiRestInterface
import hr.fjukic.app_common.sharedpref.AppSharedPreference
import io.reactivex.rxjava3.core.Observable

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Observable<UserApiResponse>
    fun requestResetPassword(resetPasswordRequest: ResetPasswordRequest): Observable<ApiResponse>
}

class AuthRepositoryImpl(
    private val sharedPref: AppSharedPreference,
    private val apiRestInterface: ApiRestInterface
) : AuthRepository {
    override fun login(loginRequest: LoginRequest): Observable<UserApiResponse> {
        return apiRestInterface.login(loginRequest).doOnNext {
            sharedPref.authToken = it.user.accessToken
            sharedPref.refreshToken = it.user.refreshToken
            sharedPref.user = it.user
        }
    }

    override fun requestResetPassword(resetPasswordRequest: ResetPasswordRequest): Observable<ApiResponse> {
        return apiRestInterface.requestResetPassword(resetPasswordRequest)
    }
}