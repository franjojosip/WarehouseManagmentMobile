package hr.fjukic.app_common.restinterface

import hr.fjukic.app_common.model.request.LoginRequest
import hr.fjukic.app_common.model.response.UserApiResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiRestInterface {
    @POST("users/login")
    fun login(@Body loginRequest: LoginRequest): Observable<UserApiResponse>
    companion object {
        var BASE_URL = "https://upravljanjeskladistimaserver.herokuapp.com/"
    }
}