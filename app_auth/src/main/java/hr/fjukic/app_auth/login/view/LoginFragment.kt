package hr.fjukic.app_auth.login.view

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import hr.fjukic.app_auth.R
import hr.fjukic.app_auth.databinding.FragmentLoginBinding
import hr.fjukic.app_auth.login.viewmodel.LoginVM
import hr.fjukic.app_common.constants.Constants
import hr.fjukic.app_common.extensions.rxInput
import hr.fjukic.app_common.view.AppFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class LoginFragment : AppFragment<FragmentLoginBinding>() {

    override val layoutId: Int = R.layout.fragment_login
    private val viewModel: LoginVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginVM = viewModel
        setupClickEvents()
        setupObservers()
        setupEmailTextInput()
        setupPasswordTextInput()
    }

    private fun setupObservers() {
        viewModel.screenAdapter.emailUI.observe(viewLifecycleOwner, {
            it?.let {
                binding.emailATIV.isErrorEnabled = it.isValid.not()
                binding.emailATIV.error =
                    if (it.isValid) null else getString(R.string.login_email_error)
            }
        })
        viewModel.screenAdapter.passwordUI.observe(viewLifecycleOwner, {
            it?.let {
                binding.passwordATIV.isErrorEnabled = it.isValid.not()
                binding.passwordATIV.error =
                    if (it.isValid) null else getString(R.string.login_password_error)
            }
        })
    }

    private fun setupEmailTextInput() {
        binding.emailATIV.editText?.filters =
            arrayOf(InputFilter.LengthFilter(Constants.TEXT_INPUT_MAX_LENGTH))

        binding.emailATIV.editText?.rxInput()
            ?.debounce(Constants.TEXT_INPUT_DEBOUNCE, TimeUnit.MILLISECONDS)?.subscribeToView {
                viewModel.handleEmailInput(it)
            }
    }

    private fun setupPasswordTextInput() {
        binding.passwordATIV.editText?.filters =
            arrayOf(InputFilter.LengthFilter(Constants.TEXT_INPUT_MAX_LENGTH))

        binding.passwordATIV.editText?.rxInput()
            ?.debounce(Constants.TEXT_INPUT_DEBOUNCE, TimeUnit.MILLISECONDS)?.subscribeToView {
                viewModel.handlePasswordInput(it)
            }
    }

    private fun setupClickEvents() {
        binding.btnContinue.setOnClickListener {
            viewModel.handleContinueClicked()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}