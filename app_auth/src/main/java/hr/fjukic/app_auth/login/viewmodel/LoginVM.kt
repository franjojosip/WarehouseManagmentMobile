package hr.fjukic.app_auth.login.viewmodel

import com.google.gson.Gson
import hr.fjukic.app_auth.login.adapter.LoginScreenAdapter
import hr.fjukic.app_auth.login.model.AppTextInputUI
import hr.fjukic.app_common.model.EventUI
import hr.fjukic.app_common.model.request.LoginRequest
import hr.fjukic.app_common.repository.auth.AuthRepository
import hr.fjukic.app_common.utils.FieldValidatorUtil
import hr.fjukic.app_common.viewmodel.AppVM
import io.reactivex.rxjava3.kotlin.addTo

class LoginVM(
    override val screenAdapter: LoginScreenAdapter,
    override val gson: Gson,
    private val authRepository: AuthRepository
) : AppVM() {
    fun handleContinueClicked() {
        val email = screenAdapter.emailUI.value?.value ?: ""
        val password = screenAdapter.passwordUI.value?.value ?: ""
        if (checkFields(email, password)
        ) {
            screenAdapter.loaderUI.postValue(EventUI.LoaderUI(true))
            authRepository.login(LoginRequest(email, password))
                .subscribeIO()
                .observeMain()
                .subscribeObservable()
                .addTo(compositeDisposable)
        }
    }

    fun handleEmailInput(value: String) {
        screenAdapter.emailUI.postValue(
            AppTextInputUI.TextInputUI(
                value,
                FieldValidatorUtil.checkIsEmailValid(value)
            )
        )
        screenAdapter.isContinueButtonEnabled.postValue(
            checkFields(
                value,
                screenAdapter.passwordUI.value?.value ?: ""
            )
        )
    }

    fun handlePasswordInput(value: String) {
        screenAdapter.passwordUI.postValue(
            AppTextInputUI.TextInputUI(
                value,
                FieldValidatorUtil.checkIsPasswordValid(value)
            )
        )
        screenAdapter.isContinueButtonEnabled.postValue(
            checkFields(
                screenAdapter.emailUI.value?.value ?: "", value
            )
        )
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