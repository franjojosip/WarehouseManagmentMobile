package hr.fjukic.app_common.repository.resource

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes

interface ResourceRepository {
    fun getResources(): Resources
    fun getString(@StringRes id: Int): String
    fun getFormattedString(@StringRes id: Int, params: Any): String
}

class ResourceRepositoryImpl(private val context: Context) : ResourceRepository {
    override fun getResources(): Resources = context.resources
    override fun getString(id: Int) = context.resources.getString(id)
    override fun getFormattedString(id: Int, params: Any) = context.resources.getString(id, params)
}