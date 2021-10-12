package hr.fjukic.app_auth.login.model

sealed class AppTextInputUI{
    data class TextInputUI(val value: String, val isValid: Boolean): AppTextInputUI()
}
