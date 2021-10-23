package hr.fjukic.app_home.container.adapter

import androidx.lifecycle.MutableLiveData
import hr.fjukic.app_common.adapter.ScreenAdapterImpl

class MainScreenContainerScreenAdapter : ScreenAdapterImpl() {
    val headerTitle: MutableLiveData<String> by lazy { MutableLiveData() }
}