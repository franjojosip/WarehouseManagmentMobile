package hr.fjukic.app_common.viewmodel

import androidx.lifecycle.ViewModel
import hr.fjukic.app_common.delegates.AppErrorHandler
import hr.fjukic.app_common.router.AppRouter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class AppVM(val router: AppRouter) : ViewModel(), AppErrorHandler {
    protected var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun <T> Observable<T>.subscribeIO(): Observable<T> = this.subscribeOn(Schedulers.io())
    fun <T> Observable<T>.observeMain(): Observable<T> =
        this.observeOn(AndroidSchedulers.mainThread())
}