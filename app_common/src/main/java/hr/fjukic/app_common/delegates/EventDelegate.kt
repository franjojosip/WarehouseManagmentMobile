package hr.fjukic.app_common.delegates

import hr.fjukic.app_common.model.EventUI

interface EventDelegate {
    fun showToast(toastUI: EventUI.ToastUI) {}
    fun showSnackbar(snackbar: EventUI.SnackbarUI) {}
    fun showLoader() {}
    fun hideLoader() {}
    fun hideKeyboard() {}
}