package hr.fjukic.app_auth.forgotpassword.viewmodel

import com.google.gson.Gson
import hr.fjukic.app_auth.R
import hr.fjukic.app_auth.forgotpassword.adapter.ForgotPasswordScreenAdapter
import hr.fjukic.app_common.model.AppTextInputUI
import hr.fjukic.app_common.model.EventUI
import hr.fjukic.app_common.model.request.ResetPasswordRequest
import hr.fjukic.app_common.repository.auth.AuthRepository
import hr.fjukic.app_common.repository.resource.ResourceRepository
import hr.fjukic.app_common.utils.FieldValidatorUtil
import hr.fjukic.app_common.viewmodel.AppVM
import io.reactivex.rxjava3.kotlin.addTo

class ForgotPasswordVM(
    override val screenAdapter: ForgotPasswordScreenAdapter,
    override val gson: Gson,
    private val authRepository: AuthRepository,
    private val resourceRepository: ResourceRepository
) : AppVM() {

    fun handleEmailInput(value: String) {
        var error: String? = null
        val isEmailValid = FieldValidatorUtil.checkIsEmailValid(value)

        if (isEmailValid.not()) {
            error = resourceRepository.getString(R.string.login_email_error)
        }
        screenAdapter.emailUI.postValue(AppTextInputUI.TextInputUI(value, error))
        screenAdapter.isResetPasswordEnabled.postValue(isEmailValid)
    }

    fun handleForgotPasswordClicked() {
        if (screenAdapter.isEmailAlreadySent.value == true) {
            screenAdapter.snackbarUI.postValue(EventUI.SnackbarUI(resourceRepository.getString(R.string.reset_password_email_already_sent)))
            return
        }

        val email = screenAdapter.emailUI.value?.value ?: ""
        if (FieldValidatorUtil.checkIsEmailValid(email)) {
            screenAdapter.loaderUI.postValue(EventUI.LoaderUI(true))
            authRepository.requestResetPassword(ResetPasswordRequest(email))
                .subscribeIO()
                .observeMain()
                .subscribeObservable(onNext = {
                    screenAdapter.isEmailAlreadySent.postValue(true)
                })
                .addTo(compositeDisposable)
        }
    }
}