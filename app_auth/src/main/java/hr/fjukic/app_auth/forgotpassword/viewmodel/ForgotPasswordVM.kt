package hr.fjukic.app_auth.forgotpassword.viewmodel

import com.google.gson.Gson
import hr.fjukic.app_auth.forgotpassword.adapter.ForgotPasswordScreenAdapter
import hr.fjukic.app_common.model.AppTextInputUI
import hr.fjukic.app_common.utils.FieldValidatorUtil
import hr.fjukic.app_common.viewmodel.AppVM

class ForgotPasswordVM(
    override val screenAdapter: ForgotPasswordScreenAdapter,
    override val gson: Gson
) : AppVM() {

    fun handleEmailInput(value: String) {
        val isEmailValid = FieldValidatorUtil.checkIsEmailValid(value)
        screenAdapter.emailUI.postValue(AppTextInputUI.TextInputUI(value, isEmailValid))
        screenAdapter.isResetPasswordEnabled.postValue(isEmailValid)
    }

    fun handleForgotPasswordClicked() {

    }
}