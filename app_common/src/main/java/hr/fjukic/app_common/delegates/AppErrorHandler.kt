package hr.fjukic.app_common.delegates

import android.widget.Toast.LENGTH_LONG
import com.google.gson.Gson
import hr.fjukic.app_common.adapter.ScreenAdapterImpl
import hr.fjukic.app_common.model.EventUI
import hr.fjukic.app_common.model.response.ErrorResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

interface AppErrorHandler {
    val screenAdapter: ScreenAdapterImpl
    val gson: Gson

    fun handleError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> handleHttpException(throwable)
            is UnknownHostException, is IOException, is ConnectException -> handleNoConnection(
                throwable
            )
            else -> handleUnknownError(throwable)
        }
    }

    fun handleHttpException(exception: HttpException) {
        if (exception.code() in 400..499) {
            val errorBody = exception.response()?.errorBody()?.string() ?: ""
            if (errorBody.isEmpty().not()) {
                try {
                    val errorModel = gson.fromJson(errorBody, ErrorResponse::class.java)
                    screenAdapter.snackbarUI.postValue(EventUI.SnackbarUI(errorModel.error, LENGTH_LONG))
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    fun handleNoConnection(throwable: Throwable) {
    }

    fun handleUnknownError(throwable: Throwable) {
    }

    fun <T : Any> Observable<T>.subscribeObservable(
        onNext: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {}
    ): Disposable = this.subscribeBy(onError = {
        handleError(it)
        onError(it)
        it.printStackTrace()
    }, onNext = onNext, onComplete = onComplete)
}