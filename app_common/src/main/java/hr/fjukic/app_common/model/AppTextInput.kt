package hr.fjukic.app_common.model

sealed class AppTextInputUI{
    data class TextInputUI(val value: String, val error: String? = null): AppTextInputUI()
}
