package hr.fjukic.app_auth.forgotpassword.adapter

import androidx.lifecycle.MutableLiveData
import hr.fjukic.app_common.adapter.ScreenAdapterImpl
import hr.fjukic.app_common.model.AppTextInputUI

class ForgotPasswordScreenAdapter: ScreenAdapterImpl() {
    val emailUI: MutableLiveData<AppTextInputUI.TextInputUI> by lazy { MutableLiveData() }
    val isResetPasswordEnabled: MutableLiveData<Boolean> by lazy { MutableLiveData() }
}