package hr.fjukic.app_common.adapter

import androidx.lifecycle.LifecycleOwner
import hr.fjukic.app_common.delegates.EventDelegate
import hr.fjukic.app_common.livedata.SingleLiveData
import hr.fjukic.app_common.model.EventUI

abstract class ScreenAdapterImpl {
    val loaderUI: SingleLiveData<EventUI.LoaderUI> by lazy { SingleLiveData() }
    val toastUI: SingleLiveData<EventUI.ToastUI> by lazy { SingleLiveData() }
    val snackbarUI: SingleLiveData<EventUI.SnackbarUI> by lazy { SingleLiveData() }

    fun observe(lifecycleOwner: LifecycleOwner, eventDelegate: EventDelegate) {
        observeLoader(lifecycleOwner, eventDelegate)
        observeToast(lifecycleOwner, eventDelegate)
        observeSnackbar(lifecycleOwner, eventDelegate)
    }

    private fun observeSnackbar(lifecycleOwner: LifecycleOwner, eventDelegate: EventDelegate) {
        snackbarUI.observe(lifecycleOwner, { eventDelegate.showSnackbar(it) })
    }

    private fun observeToast(lifecycleOwner: LifecycleOwner, eventDelegate: EventDelegate) {
        toastUI.observe(lifecycleOwner, {
            eventDelegate.showToast(it)
        })
    }

    private fun observeLoader(lifecycleOwner: LifecycleOwner, eventDelegate: EventDelegate) {
        loaderUI.observe(lifecycleOwner, {
            when (it.isEnabled) {
                true -> eventDelegate.showLoader()
                else -> eventDelegate.hideLoader()
            }
        })
    }
}