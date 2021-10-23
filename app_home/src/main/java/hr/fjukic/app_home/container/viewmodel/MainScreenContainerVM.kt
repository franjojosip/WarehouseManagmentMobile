package hr.fjukic.app_home.container.viewmodel

import com.google.gson.Gson
import hr.fjukic.app_common.repository.resource.ResourceRepository
import hr.fjukic.app_common.repository.user.UserRepository
import hr.fjukic.app_common.router.AppRouter
import hr.fjukic.app_common.viewmodel.AppVM
import hr.fjukic.app_home.R
import hr.fjukic.app_home.container.adapter.MainScreenContainerScreenAdapter

class MainScreenContainerVM(
    router: AppRouter,
    override val screenAdapter: MainScreenContainerScreenAdapter,
    override val gson: Gson,
    private val userRepository: UserRepository,
    private val resourceRepository: ResourceRepository
) : AppVM(router) {
    fun init() {
        if (screenAdapter.headerTitle.value == null) {
            val user = userRepository.getUser()
            user?.let {
                val headerTitle = resourceRepository.getFormattedString(
                    R.string.header_title,
                    "${user.fname} ${user.lname}"
                )
                screenAdapter.headerTitle.postValue(headerTitle)
            }
        }
    }
}