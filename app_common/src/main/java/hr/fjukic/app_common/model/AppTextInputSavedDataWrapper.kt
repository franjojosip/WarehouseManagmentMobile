package hr.fjukic.app_common.model

import android.view.View

data class AppTextInputSavedDataWrapper(val stateData: AppTextInputViewSavedStateData): View.BaseSavedState(stateData)