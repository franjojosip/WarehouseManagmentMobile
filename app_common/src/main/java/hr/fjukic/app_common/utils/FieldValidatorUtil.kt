package hr.fjukic.app_common.utils

import android.util.Patterns

object FieldValidatorUtil {
    private const val MIN_EMAIL_LENGTH = 5
    private const val MIN_PASSWORD_LENGTH = 6

    fun checkIsEmailValid(value: String) =
        value.length >= MIN_EMAIL_LENGTH && Patterns.EMAIL_ADDRESS.matcher(value).matches()

    fun checkIsPasswordValid(value: String) = value.length >= MIN_PASSWORD_LENGTH
}