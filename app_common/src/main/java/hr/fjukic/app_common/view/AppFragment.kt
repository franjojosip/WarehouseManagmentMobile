package hr.fjukic.app_common.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import hr.fjukic.app_common.delegates.EventDelegate
import hr.fjukic.app_common.R
import hr.fjukic.app_common.adapter.ScreenAdapterImpl
import hr.fjukic.app_common.model.EventUI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class AppFragment<VM : ViewModel, ViewBinding : ViewDataBinding> : Fragment(),
    EventDelegate {
    abstract val layoutId: Int
    abstract val viewModel: VM
    var binding: ViewBinding? = null
    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding?.apply { lifecycleOwner = viewLifecycleOwner }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.addAll(disposable)
    }

    fun <T> Observable<T>.subscribeToView(onNext: (T) -> Unit = {}) = addDisposable(
        this.observeOn(AndroidSchedulers.mainThread()).subscribe(
            onNext, {
                it.printStackTrace()
            }
        )
    )

    fun setEventDelegate(screenAdapter: ScreenAdapterImpl) {
        screenAdapter.observe(viewLifecycleOwner, this)
    }

    override fun showToast(toastUI: EventUI.ToastUI) {
        Toast.makeText(context, toastUI.message, toastUI.duration).show()
    }

    override fun showSnackbar(snackbar: EventUI.SnackbarUI) {
        binding?.root?.let {
            Snackbar.make(it, snackbar.message, BaseTransientBottomBar.LENGTH_INDEFINITE)
                .apply {
                    setTextColor(ContextCompat.getColor(it.context, R.color.colorGrayDark70))
                    duration = 3000
                }.show()
        }
    }

    override fun showLoader() {}
    override fun hideLoader() {}

    override fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }
}
