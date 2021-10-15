package hr.fjukic.app_common.view

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.core.os.bundleOf
import com.google.android.material.textfield.TextInputLayout
import hr.fjukic.app_common.R
import hr.fjukic.app_common.model.AppTextInputSavedDataWrapper
import hr.fjukic.app_common.model.AppTextInputViewSavedStateData

class AppTextInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.textInputStyle
) : TextInputLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.text_input_view, this)
    }

    override fun onSaveInstanceState(): Parcelable {
        val savedState = super.onSaveInstanceState()
        val stateData = AppTextInputViewSavedStateData(savedState, editText?.text.toString())
        return AppTextInputSavedDataWrapper(stateData)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val stateWrapper = state as? AppTextInputSavedDataWrapper
        editText?.setText(stateWrapper?.stateData?.value)
        super.onRestoreInstanceState(stateWrapper?.stateData?.savedStateData)
    }
}