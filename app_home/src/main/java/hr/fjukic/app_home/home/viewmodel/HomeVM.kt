package hr.fjukic.app_home.home.viewmodel

import com.google.gson.Gson
import hr.fjukic.app_common.repository.resource.ResourceRepository
import hr.fjukic.app_common.repository.user.UserRepository
import hr.fjukic.app_common.viewmodel.AppVM
import hr.fjukic.app_home.R
import hr.fjukic.app_home.home.adapter.HomeScreenAdapter
import hr.fjukic.app_home.home.model.HomeCardUI
import hr.fjukic.app_home.home.utils.HomeScreenElementType

class HomeVM(
    override val screenAdapter: HomeScreenAdapter,
    override val gson: Gson,
    private val resourceRepository: ResourceRepository,
    private val userRepository: UserRepository
) : AppVM() {

    fun init() {
        if (screenAdapter.homeCards.value.isNullOrEmpty()) {
            screenAdapter.homeCards.postValue(getHomeCards())
        }
        if (screenAdapter.managementTitles.value.isNullOrEmpty()) {
            screenAdapter.managementTitles.postValue(getManagementTitleCards())
        }
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

    private fun getManagementTitleCards(): MutableList<HomeCardUI> {
        val managementTitles = mutableListOf(
            resourceRepository.getString(R.string.warehouses),
            resourceRepository.getString(R.string.stocks),
            resourceRepository.getString(R.string.products),
            resourceRepository.getString(R.string.categories),
            resourceRepository.getString(R.string.subcategories),
            resourceRepository.getString(R.string.packagings),
            resourceRepository.getString(R.string.citites),
            resourceRepository.getString(R.string.locations)
        )
        return managementTitles.map {
            HomeCardUI(
                title = it,
                elementType = HomeScreenElementType.MANAGMENT_CARD
            )
        }.toMutableList()
    }

    private fun getHomeCards(): MutableList<HomeCardUI> {
        return mutableListOf(
            HomeCardUI(
                resourceRepository.getString(R.string.total_employees),
                resourceRepository.getString(R.string.total_employees_description),
                drawableId = R.drawable.users
            ),
            HomeCardUI(
                resourceRepository.getString(R.string.total_entries),
                resourceRepository.getString(R.string.total_entries_description),
                drawableId = R.drawable.warehouse
            ),
            HomeCardUI(
                resourceRepository.getString(R.string.total_receipts),
                resourceRepository.getString(R.string.total_reciepts_description),
                drawableId = R.drawable.reciepts
            ),
            HomeCardUI(
                resourceRepository.getString(R.string.total_stocktakings),
                resourceRepository.getString(R.string.total_stoctakings_description),
                drawableId = R.drawable.stocktakings
            )
        )
    }
}