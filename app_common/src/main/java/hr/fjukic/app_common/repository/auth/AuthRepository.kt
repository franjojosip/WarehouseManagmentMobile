package hr.fjukic.app_common.repository.auth

import hr.fjukic.app_common.model.request.LoginRequest
import hr.fjukic.app_common.model.response.UserApiResponse
import io.reactivex.rxjava3.core.Observable

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Observable<UserApiResponse>
}