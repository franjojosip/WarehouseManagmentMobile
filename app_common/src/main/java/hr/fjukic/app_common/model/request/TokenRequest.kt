package hr.fjukic.app_common.model.request

data class TokenRequest(
    val accessToken: String? = "",
    val refreshToken: String? = ""
)
