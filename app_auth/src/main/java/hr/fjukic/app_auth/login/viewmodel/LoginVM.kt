package hr.fjukic.app_auth.login.viewmodel

import androidx.lifecycle.ViewModel
import hr.fjukic.app_auth.login.adapter.LoginScreenAdapter
import hr.fjukic.app_auth.login.model.AppTextInputUI
import hr.fjukic.app_common.utils.FieldValidatorUtil

class LoginVM : ViewModel() {
    val screenAdapter = LoginScreenAdapter()

    fun handleContinueClicked() {
        if (checkFields(
                screenAdapter.emailUI.value?.value ?: "",
                screenAdapter.passwordUI.value?.value ?: ""
            )
        ) {
            //TODO Call server to login into application
        }
    }

    fun handleEmailInput(value: String) {
        screenAdapter.emailUI.postValue(AppTextInputUI.TextInputUI(value, FieldValidatorUtil.checkIsEmailValid(value)))
        screenAdapter.isContinueButtonEnabled.postValue(checkFields(value, screenAdapter.passwordUI.value?.value ?: ""))
    }

    fun handlePasswordInput(value: String) {
        screenAdapter.passwordUI.postValue(AppTextInputUI.TextInputUI(value, FieldValidatorUtil.checkIsPasswordValid(value)))
        screenAdapter.isContinueButtonEnabled.postValue(checkFields(screenAdapter.emailUI.value?.value ?: "", value))
    }

    private fun checkFields(
        email: String,
        password: String
    ): Boolean {
        return FieldValidatorUtil.checkIsEmailValid(email) && FieldValidatorUtil.checkIsPasswordValid(
            password
        )
    }
}