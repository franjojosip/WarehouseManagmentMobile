package hr.fjukic.app_auth.login.adapter

import android.text.SpannableString
import androidx.lifecycle.MutableLiveData
import hr.fjukic.app_common.model.AppTextInputUI.TextInputUI
import hr.fjukic.app_common.adapter.ScreenAdapterImpl

class LoginScreenAdapter : ScreenAdapterImpl() {
    val isContinueButtonEnabled: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val emailUI: MutableLiveData<TextInputUI> by lazy { MutableLiveData() }
    val passwordUI: MutableLiveData<TextInputUI> by lazy { MutableLiveData() }
    val forgotPassword : MutableLiveData<SpannableString> by lazy { MutableLiveData() }
}