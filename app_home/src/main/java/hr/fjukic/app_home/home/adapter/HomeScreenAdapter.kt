package hr.fjukic.app_home.home.adapter

import androidx.lifecycle.MutableLiveData
import hr.fjukic.app_common.adapter.ScreenAdapterImpl
import hr.fjukic.app_home.home.model.HomeCardUI

class HomeScreenAdapter : ScreenAdapterImpl() {
    val homeCards: MutableLiveData<MutableList<HomeCardUI>> by lazy { MutableLiveData() }
    val managementTitles: MutableLiveData<MutableList<HomeCardUI>> by lazy { MutableLiveData() }
    val headerTitle: MutableLiveData<String> by lazy { MutableLiveData() }
}