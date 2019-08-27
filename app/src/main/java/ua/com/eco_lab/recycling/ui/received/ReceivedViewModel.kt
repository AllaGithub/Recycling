package ua.com.eco_lab.recycling.ui.received

import android.app.Application
import ua.com.eco_lab.recycling.data.AppRoomDatabase
import ua.com.eco_lab.recycling.data.repository.ReceiptRepository
import ua.com.eco_lab.recycling.ui.BaseViewModel

class ReceivedViewModel(application: Application) : BaseViewModel(application) {

    private val receiptRepository: ReceiptRepository

    init {
        val receiptsDao = AppRoomDatabase.getDatabase(application).receiptDao()
        val equipmentDao = AppRoomDatabase.getDatabase(application).equipmentDao()
        receiptRepository = ReceiptRepository(receiptsDao, equipmentDao)
    }
}