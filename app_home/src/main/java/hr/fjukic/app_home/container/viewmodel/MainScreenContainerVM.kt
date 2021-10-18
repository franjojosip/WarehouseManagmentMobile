package hr.fjukic.app_home.container.viewmodel

import com.google.gson.Gson
import hr.fjukic.app_common.viewmodel.AppVM
import hr.fjukic.app_home.container.adapter.MainScreenContainerScreenAdapter

class MainScreenContainerVM(
    override val screenAdapter: MainScreenContainerScreenAdapter,
    override val gson: Gson
) : AppVM() {
}