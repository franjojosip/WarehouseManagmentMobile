package hr.fjukic.app_common.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class AppFragment<ViewBinding : ViewDataBinding> : Fragment() {
    abstract val layoutId: Int
    lateinit var binding: ViewBinding private set
    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.apply { lifecycleOwner = viewLifecycleOwner }
        return binding.root
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
}
