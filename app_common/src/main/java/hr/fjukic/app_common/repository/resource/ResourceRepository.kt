package hr.fjukic.app_common.repository.resource

import android.content.res.Resources
import androidx.annotation.StringRes

interface ResourceRepository {
    fun getResources(): Resources
    fun getString(@StringRes id: Int): String
    fun getFormattedString(@StringRes id: Int, params: Any): String
}