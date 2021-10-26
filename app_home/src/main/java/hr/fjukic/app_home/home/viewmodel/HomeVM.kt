package hr.fjukic.app_home.home.viewmodel

import com.google.gson.Gson
import hr.fjukic.app_common.model.EventUI
import hr.fjukic.app_common.model.response.Home
import hr.fjukic.app_common.repository.HomeRepository
import hr.fjukic.app_common.repository.resource.ResourceRepository
import hr.fjukic.app_common.router.AppRouter
import hr.fjukic.app_common.viewmodel.AppVM
import hr.fjukic.app_home.R
import hr.fjukic.app_home.home.adapter.HomeScreenAdapter
import hr.fjukic.app_home.home.model.HomeCardUI
import hr.fjukic.app_home.home.utils.HomeScreenElementType
import io.reactivex.rxjava3.kotlin.addTo

class HomeVM(
    override val router: AppRouter,
    override val screenAdapter: HomeScreenAdapter,
    override val gson: Gson,
    private val resourceRepository: ResourceRepository,
    private val homeRepository: HomeRepository
) : AppVM() {

    fun init() {
        screenAdapter.loaderUI.postValue(EventUI.LoaderUI(true))
        if (screenAdapter.managementTitles.value.isNullOrEmpty()) {
            screenAdapter.managementTitles.postValue(getManagementTitleCards())
        }
        if (screenAdapter.homeCards.value.isNullOrEmpty()) {
            homeRepository.getData()
                .subscribeIO()
                .observeMain()
                .subscribeObservable(onNext = {
                    screenAdapter.homeCards.postValue(getHomeCards(it))
                })
                .addTo(compositeDisposable)
        } else {
            screenAdapter.loaderUI.postValue(EventUI.LoaderUI(false))
        }
    }

    private fun getManagementTitleCards(): MutableList<HomeCardUI> {
        val managementTitles = mutableListOf(
            resourceRepository.getString(R.string.cities),
            resourceRepository.getString(R.string.products),
            resourceRepository.getString(R.string.packagings),
            resourceRepository.getString(R.string.subcategories),
            resourceRepository.getString(R.string.stocks),
            resourceRepository.getString(R.string.warehouses),
        )
        return managementTitles.map {
            HomeCardUI(
                title = it,
                elementType = HomeScreenElementType.MANAGMENT_CARD
            )
        }.toMutableList()
    }

    private fun getHomeCards(data: Home): MutableList<HomeCardUI> {
        return mutableListOf(
            HomeCardUI(
                resourceRepository.getFormattedString(R.string.total_employees, data.total_users),
                resourceRepository.getString(R.string.total_employees_description),
                drawableId = R.drawable.users
            ),
            HomeCardUI(
                resourceRepository.getFormattedString(R.string.total_entries, data.total_entries),
                resourceRepository.getString(R.string.total_entries_description),
                drawableId = R.drawable.warehouse
            ),
            HomeCardUI(
                resourceRepository.getFormattedString(R.string.total_receipts, data.total_reciepts),
                resourceRepository.getString(R.string.total_reciepts_description),
                drawableId = R.drawable.reciepts
            ),
            HomeCardUI(
                resourceRepository.getFormattedString(
                    R.string.total_stocktakings,
                    data.total_stoctakings
                ),
                resourceRepository.getString(R.string.total_stoctakings_description),
                drawableId = R.drawable.stocktakings
            )
        )
    }
}