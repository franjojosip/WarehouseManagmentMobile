package hr.fjukic.app_single_page.city.viewmodel

import com.google.gson.Gson
import hr.fjukic.app_common.adapter.ScreenAdapterImpl
import hr.fjukic.app_common.router.AppRouter
import hr.fjukic.app_common.viewmodel.AppVM
import hr.fjukic.app_single_page.city.adapter.CityScreenAdapter

class CityVM(
    override val screenAdapter: CityScreenAdapter,
    override val gson: Gson,
    override val router: AppRouter
) : AppVM() {
}