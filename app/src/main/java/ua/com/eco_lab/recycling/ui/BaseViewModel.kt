package ua.com.eco_lab.recycling.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ua.com.eco_lab.recycling.RecyclingNavigator
import ua.com.eco_lab.recycling.rx.SchedulerProvider
import java.lang.ref.WeakReference

open class BaseViewModel : ViewModel() {

    var recNavigator: WeakReference<RecyclingNavigator>? = null
    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var schedulerProvider: SchedulerProvider = SchedulerProvider()


    fun setNavigator(navigator: RecyclingNavigator) {
        recNavigator = WeakReference(navigator)
    }

    fun removeNavigator(navigator: RecyclingNavigator) {
        if (recNavigator == navigator) {
            recNavigator = null
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}