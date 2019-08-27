package ua.com.eco_lab.recycling.ui.addreceipt

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.MutableLiveData
import ua.com.eco_lab.recycling.R
import ua.com.eco_lab.recycling.data.AppRoomDatabase
import ua.com.eco_lab.recycling.data.repository.ReceiptRepository
import ua.com.eco_lab.recycling.model.Equipment
import ua.com.eco_lab.recycling.model.Receipt
import ua.com.eco_lab.recycling.observable.SingleLiveEvent
import ua.com.eco_lab.recycling.ui.BaseViewModel
import java.util.*

class AddReceiptViewModel(application: Application) : BaseViewModel(application), DatePickerDialog.OnDateSetListener {

    private val receiptRepository: ReceiptRepository


    var receipt: MutableLiveData<Receipt>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }

            return field
        }

    var equipment: MutableLiveData<Equipment>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }

            return field
        }


    val clearAllFieldsEvent = SingleLiveEvent<Void>()
    val scrollUpEvent = SingleLiveEvent<Void>()

    init {
        receipt?.value = Receipt()

        val receiptsDao = AppRoomDatabase.getDatabase(application).receiptDao()
        val equipmentDao = AppRoomDatabase.getDatabase(application).equipmentDao()
        receiptRepository = ReceiptRepository(receiptsDao, equipmentDao)
    }


    fun setDate(context: Context) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(context, this, calendar.get(Calendar.YEAR), calendar.get(
            Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        receipt?.value?.dateReceived = calendar.time
    }

    @Suppress("UNUSED_PARAMETER")
    fun addEquipment(view: View) {
        recNavigator?.get()?.navigate(R.id.addReceiptEquipmentFragment)
    }

    @Suppress("UNUSED_PARAMETER")
    fun nextEquipment(view: View) {
        equipment?.value?.let { receipt?.value?.equipments?.add(it) }
//todo: send equipment to BE
        clearAllFieldsEvent.call()
        scrollUpEvent.call()

    }

    @Suppress("UNUSED_PARAMETER")
    fun finishAddingEquipment(view: View) {
        equipment?.value?.let { receipt?.value?.equipments?.add(it) }
        //todo: send last equipment to BE
        recNavigator?.get()?.navigate(R.id.action_addReceiptEquipmentFragment_to_receivedFragment)
    }

}