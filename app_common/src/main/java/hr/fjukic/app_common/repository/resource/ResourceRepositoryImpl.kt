package hr.fjukic.app_common.repository.resource

import android.content.Context
import android.content.res.Resources

class ResourceRepositoryImpl(private val context: Context) : ResourceRepository {
    override fun getResources(): Resources = context.resources
    override fun getString(id: Int) = context.resources.getString(id)
    override fun getFormattedString(id: Int, params: Any) = context.resources.getString(id, params)
}