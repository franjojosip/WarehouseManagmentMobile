package hr.fjukic.app_auth.login.viewmodel

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import com.google.gson.Gson
import hr.fjukic.app_auth.R
import hr.fjukic.app_auth.login.adapter.LoginScreenAdapter
import hr.fjukic.app_auth.login.view.LoginFragmentDirections
import hr.fjukic.app_common.model.AppTextInputUI
import hr.fjukic.app_common.model.EventUI
import hr.fjukic.app_common.model.request.LoginRequest
import hr.fjukic.app_common.repository.auth.AuthRepository
import hr.fjukic.app_common.repository.resource.ResourceRepository
import hr.fjukic.app_common.repository.user.UserRepository
import hr.fjukic.app_common.router.AppRouter
import hr.fjukic.app_common.router.NavDirectionsWrapper
import hr.fjukic.app_common.utils.FieldValidatorUtil
import hr.fjukic.app_common.viewmodel.AppVM
import io.reactivex.rxjava3.kotlin.addTo

class LoginVM(
    router: AppRouter,
    override val screenAdapter: LoginScreenAdapter,
    override val gson: Gson,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val resourceRepository: ResourceRepository
) : AppVM(router) {

    fun init(forgotPasswordText: String, color: Int) {
        setupForgotPasswordField(forgotPasswordText, color)
    }

    private fun setupForgotPasswordField(text: String, color: Int) {
        val spannableString = SpannableString(text)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                router.navigationEvent.postValue(NavDirectionsWrapper(LoginFragmentDirections.actionLoginToForgotPassword()))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = color
            }
        }

        spannableString.setSpan(
            clickableSpan,
            0,
            spannableString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        screenAdapter.forgotPassword.postValue(spannableString)
    }

    fun handleContinueClicked() {
        val email = screenAdapter.emailUI.value?.value ?: ""
        val password = screenAdapter.passwordUI.value?.value ?: ""
        if (checkFields(email, password)
        ) {
            screenAdapter.loaderUI.postValue(EventUI.LoaderUI(true))
            authRepository.login(LoginRequest(email, password))
                .subscribeIO()
                .observeMain()
                .subscribeObservable(onNext = {
                    router.navigationEvent.postValue(NavDirectionsWrapper(LoginFragmentDirections.actionLoginToMainScreenContainer()))
                })
                .addTo(compositeDisposable)
        }
    }

    fun handleEmailInput(value: String) {
        var error: String? = null
        val isEmailValid = FieldValidatorUtil.checkIsEmailValid(value)

        if (isEmailValid.not()) {
            error = resourceRepository.getString(R.string.login_email_error)
        }
        screenAdapter.emailUI.postValue(AppTextInputUI.TextInputUI(value, error))
        screenAdapter.isContinueButtonEnabled.postValue(
            checkFields(
                value,
                screenAdapter.passwordUI.value?.value ?: ""
            )
        )
    }

    fun handlePasswordInput(value: String) {
        var error: String? = null
        val isPasswordValid = FieldValidatorUtil.checkIsPasswordValid(value)

        if (isPasswordValid.not()) {
            error = resourceRepository.getString(R.string.login_password_error)
        }
        screenAdapter.passwordUI.postValue(AppTextInputUI.TextInputUI(value, error))
        screenAdapter.isContinueButtonEnabled.postValue(
            checkFields(
                screenAdapter.emailUI.value?.value ?: "", value
            )
        )
    }

    fun handleCheckIsUserSignedIn() {
        if (userRepository.getUser() != null) {
            router.navigationEvent.postValue(NavDirectionsWrapper(LoginFragmentDirections.actionLoginToMainScreenContainer()))
        }
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