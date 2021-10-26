package hr.fjukic.app_common.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import hr.fjukic.app_common.R
import hr.fjukic.app_common.delegates.EventDelegate
import hr.fjukic.app_common.model.EventUI
import hr.fjukic.app_common.router.NavigationController
import hr.fjukic.app_common.viewmodel.AppVM
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class AppFragment<VM : AppVM, ViewBinding : ViewDataBinding> : Fragment(),
    EventDelegate {
    abstract val layoutId: Int
    abstract val viewModel: VM
    var binding: ViewBinding? = null
    private var compositeDisposable = CompositeDisposable()
    open val navigationController: NavigationController by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding?.apply { lifecycleOwner = viewLifecycleOwner }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.router.observe(viewLifecycleOwner, navigationController)
        viewModel.screenAdapter.observe(viewLifecycleOwner, this)
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

    fun <T> LiveData<T>.observeWithNotNull(observer: (T) -> Unit) =
        this.observe(viewLifecycleOwner) { it?.let(observer) }

    override fun showToast(toastUI: EventUI.ToastUI) {
        Toast.makeText(context, toastUI.message, toastUI.duration).show()
    }

    override fun showSnackbar(snackbar: EventUI.SnackbarUI) {
        binding?.root?.let {
            Snackbar.make(it, snackbar.message, BaseTransientBottomBar.LENGTH_INDEFINITE)
                .apply {
                    setTextColor(ContextCompat.getColor(it.context, R.color.colorWhite))
                    duration = 3000
                }.show()
        }
    }

    override fun showLoader() {
        binding?.root?.findViewById<LoaderView>(R.id.loader)?.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        binding?.root?.findViewById<LoaderView>(R.id.loader)?.visibility = View.GONE

    }

    override fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }
}
