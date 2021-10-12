package hr.fjukic.app_common.extensions

import android.widget.EditText
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.core.Observable

fun EditText.rxInput(): Observable<String> = this.textChanges().skipInitialValue().map(CharSequence::toString)