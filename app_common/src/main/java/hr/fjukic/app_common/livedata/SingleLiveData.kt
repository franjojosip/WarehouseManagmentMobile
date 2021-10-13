package hr.fjukic.app_common.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class SingleLiveData<T> : MutableLiveData<T>() {
    private val isConsumed = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if(hasActiveObservers()) return
        super.observe(owner, {
            if (isConsumed.compareAndSet(false, true)) {
                observer.onChanged(it)
            }
        })
    }

    override fun setValue(value: T) {
        isConsumed.set(false)
        super.setValue(value)
    }

    override fun postValue(value: T) {
        isConsumed.set(false)
        super.postValue(value)
    }

}