package ua.com.eco_lab.recycling.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ua.com.eco_lab.recycling.RecyclingNavigator
import java.lang.ref.WeakReference

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var recNavigator: WeakReference<RecyclingNavigator>? = null

    fun setNavigator(navigator: RecyclingNavigator) {
        recNavigator = WeakReference(navigator)
    }

    fun removeNavigator(navigator: RecyclingNavigator) {
        if (recNavigator == navigator) {
            recNavigator = null
        }
    }

}