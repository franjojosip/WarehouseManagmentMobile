package hr.fjukic.app_common.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import hr.fjukic.app_common.R

class LoaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.textInputStyle
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.loader_layout, this)
        id = R.id.loader
    }
}