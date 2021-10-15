package hr.fjukic.app_common.restinterface

import hr.fjukic.app_common.model.request.LoginRequest
import hr.fjukic.app_common.model.request.ResetPasswordRequest
import hr.fjukic.app_common.model.response.ApiResponse
import hr.fjukic.app_common.model.response.UserApiResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiRestInterface {

    /**
    Authentication routes
     **/

    @POST("users/login")
    fun login(@Body loginRequest: LoginRequest): Observable<UserApiResponse>

    @POST("users/requestresetpassword")
    fun requestResetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Observable<ApiResponse>
}