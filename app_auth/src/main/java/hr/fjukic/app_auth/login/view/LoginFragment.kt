package hr.fjukic.app_auth.login.view

import android.os.Bundle
import android.text.InputFilter
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import hr.fjukic.app_auth.R
import hr.fjukic.app_auth.databinding.FragmentLoginBinding
import hr.fjukic.app_auth.login.viewmodel.LoginVM
import hr.fjukic.app_common.constants.Constants
import hr.fjukic.app_common.extensions.rxInput
import hr.fjukic.app_common.view.AppFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class LoginFragment : AppFragment<LoginVM, FragmentLoginBinding>() {

    override val layoutId: Int = R.layout.fragment_login
    override val viewModel: LoginVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.handleCheckIsUserSignedIn()
        viewModel.init(
            getString(R.string.forgot_password),
            ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loginVM = viewModel
        setupClickEvents()
        setupObservers()
        setupEmailTextInput()
        setupPasswordTextInput()
    }

    private fun setupObservers() {
        viewModel.screenAdapter.emailUI.observeWithNotNull {
            binding?.emailATIV?.apply {
                isErrorEnabled = it.error != null
                error = it.error
            }
        }
        viewModel.screenAdapter.passwordUI.observeWithNotNull {
            binding?.passwordATIV?.apply {
                isErrorEnabled = it.error != null
                error = it.error
            }
        }
        viewModel.screenAdapter.forgotPassword.observeWithNotNull {
            binding?.tvForgotPassword?.apply {
                text = it
                movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

    private fun setupPasswordTextInput() {
        binding?.passwordATIV?.editText?.apply {
            filters = arrayOf(InputFilter.LengthFilter(Constants.TEXT_INPUT_MAX_LENGTH))
            imeOptions = EditorInfo.IME_ACTION_DONE
        }

        binding?.passwordATIV?.editText?.rxInput()
            ?.debounce(Constants.TEXT_INPUT_DEBOUNCE, TimeUnit.MILLISECONDS)?.subscribeToView {
                viewModel.handlePasswordInput(it)
            }
    }

    private fun setupEmailTextInput() {
        binding?.emailATIV?.editText?.apply {
            filters = arrayOf(InputFilter.LengthFilter(Constants.TEXT_INPUT_MAX_LENGTH))
            imeOptions = EditorInfo.IME_ACTION_NEXT
        }

        binding?.emailATIV?.editText?.rxInput()
            ?.debounce(Constants.TEXT_INPUT_DEBOUNCE, TimeUnit.MILLISECONDS)?.subscribeToView {
                viewModel.handleEmailInput(it)
            }
    }

    private fun setupClickEvents() {
        binding?.btnContinue?.setOnClickListener {
            hideKeyboard()
            viewModel.handleContinueClicked()
        }
    }

    override fun showLoader() {
        binding?.loaderLayout?.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        binding?.loaderLayout?.visibility = View.GONE
    }
}