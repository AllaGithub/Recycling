package ua.com.eco_lab.recycling.ui.received

import android.app.Application
import androidx.lifecycle.MutableLiveData
import ua.com.eco_lab.recycling.mapper.ReceiptMapper
import ua.com.eco_lab.recycling.model.Receipt
import ua.com.eco_lab.recycling.ui.BaseViewModel

class ReceivedViewModel(application: Application) : BaseViewModel(application) {

    var receiptList: MutableLiveData<List<Receipt>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                field?.value = arrayListOf()
            }

            return field
        }

    fun refreshData() {
        receiptRepository.getAllReceipt()
            .compose(schedulerProvider.ioToMainSingleScheduler())
            .subscribe { list, t ->
                receiptList?.value = list.map { ReceiptMapper.parse(it) }

            }.apply { compositeDisposable.add(this) }

    }
}