package hr.fjukic.app_common.model

import android.widget.Toast.LENGTH_SHORT

sealed class EventUI {
    data class ToastUI(val message: String, val duration: Int = LENGTH_SHORT) : EventUI()
    data class SnackbarUI(val message: String, val duration: Int = LENGTH_SHORT) : EventUI()
    data class LoaderUI(val isEnabled: Boolean) : EventUI()
}
