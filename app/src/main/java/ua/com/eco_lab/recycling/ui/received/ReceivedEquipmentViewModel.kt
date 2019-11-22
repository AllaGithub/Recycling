package ua.com.eco_lab.recycling.ui.received

import android.app.Application
import androidx.lifecycle.MutableLiveData
import ua.com.eco_lab.recycling.mapper.EquipmentMapper
import ua.com.eco_lab.recycling.model.Equipment
import ua.com.eco_lab.recycling.ui.BaseViewModel

class ReceivedEquipmentViewModel(application: Application) : BaseViewModel(application) {

    var receiptId: MutableLiveData<Long>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }

            return field
        }

    var equipmentList: MutableLiveData<List<Equipment>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                field?.value = arrayListOf()
            }

            return field
        }

    fun refreshData() {

        receiptId?.value?.let { id ->
            receiptRepository.getAllEquipmentByReceiptId(id)
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe { list, t ->
                    equipmentList?.value = list.map { EquipmentMapper.parse(it) }

                }.apply { compositeDisposable.add(this) }
        }
    }
}