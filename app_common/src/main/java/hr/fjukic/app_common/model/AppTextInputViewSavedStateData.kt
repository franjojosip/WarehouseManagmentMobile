package hr.fjukic.app_common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppTextInputViewSavedStateData(
    val savedStateData: Parcelable?,
    val value: String? = ""
) : Parcelable
