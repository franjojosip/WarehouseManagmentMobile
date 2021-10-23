package hr.fjukic.app_auth.forgotpassword.view

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.view.inputmethod.EditorInfo
import hr.fjukic.app_auth.R
import hr.fjukic.app_auth.databinding.FragmentForgotPasswordBinding
import hr.fjukic.app_auth.forgotpassword.viewmodel.ForgotPasswordVM
import hr.fjukic.app_common.constants.Constants
import hr.fjukic.app_common.extensions.rxInput
import hr.fjukic.app_common.view.AppFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class ForgotPasswordFragment : AppFragment<ForgotPasswordVM, FragmentForgotPasswordBinding>() {
    override val layoutId: Int = R.layout.fragment_forgot_password
    override val viewModel: ForgotPasswordVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loginVM = viewModel

        setupClickEvents()
        setupObservers()
        setupEmailTextInput()
    }

    private fun setupClickEvents() {
        binding?.btnResetPassword?.setOnClickListener {
            hideKeyboard()
            viewModel.handleForgotPasswordClicked()
        }
    }

    private fun setupObservers() {
        viewModel.screenAdapter.emailUI.observeWithNotNull {
            binding?.emailATIV?.apply {
                isErrorEnabled = it.error != null
                error = it.error
            }
        }
    }

    private fun setupEmailTextInput() {
        binding?.emailATIV?.editText?.apply {
            filters = arrayOf(InputFilter.LengthFilter(Constants.TEXT_INPUT_MAX_LENGTH))
            imeOptions = EditorInfo.IME_ACTION_DONE
        }

        binding?.emailATIV?.editText?.rxInput()
            ?.debounce(Constants.TEXT_INPUT_DEBOUNCE, TimeUnit.MILLISECONDS)?.subscribeToView {
                viewModel.handleEmailInput(it)
            }
    }
}