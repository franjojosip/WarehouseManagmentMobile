package hr.fjukic.app_common.model.response

data class User(
    val id: String,
    val fname: String,
    val lname: String,
    val email: String,
    val role: String,
    val accessToken: String,
    val refreshToken: String,
)