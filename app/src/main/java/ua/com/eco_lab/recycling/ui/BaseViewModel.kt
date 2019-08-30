package ua.com.eco_lab.recycling.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import ua.com.eco_lab.recycling.RecyclingNavigator
import ua.com.eco_lab.recycling.data.AppRoomDatabase
import ua.com.eco_lab.recycling.data.repository.ReceiptRepository
import ua.com.eco_lab.recycling.rx.SchedulerProvider
import java.lang.ref.WeakReference

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val receiptRepository: ReceiptRepository

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val schedulerProvider: SchedulerProvider = SchedulerProvider()

    var recNavigator: WeakReference<RecyclingNavigator>? = null

    init {
        val receiptsDao = AppRoomDatabase.getDatabase(application).receiptDao()
        val equipmentDao = AppRoomDatabase.getDatabase(application).equipmentDao()
        receiptRepository = ReceiptRepository(receiptsDao, equipmentDao)
    }


    fun setNavigator(navigator: RecyclingNavigator) {
        recNavigator = WeakReference(navigator)
    }

    fun removeNavigator(navigator: RecyclingNavigator) {
        if (recNavigator == navigator) {
            recNavigator = null
        }
    }

}