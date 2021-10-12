package hr.fjukic.app_common.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import hr.fjukic.app_common.R

class AppTextInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.textInputStyle
) : TextInputLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.text_input_view, this)
    }
}