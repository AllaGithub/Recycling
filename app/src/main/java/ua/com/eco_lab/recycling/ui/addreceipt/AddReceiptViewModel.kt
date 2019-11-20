package ua.com.eco_lab.recycling.ui.addreceipt

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.MutableLiveData
import ua.com.eco_lab.recycling.R
import ua.com.eco_lab.recycling.mapper.EquipmentMapper
import ua.com.eco_lab.recycling.mapper.ReceiptMapper
import ua.com.eco_lab.recycling.model.Equipment
import ua.com.eco_lab.recycling.model.Receipt
import ua.com.eco_lab.recycling.observable.SingleLiveEvent
import ua.com.eco_lab.recycling.ui.BaseViewModel
import java.util.*

class AddReceiptViewModel(application: Application) : BaseViewModel(application), DatePickerDialog.OnDateSetListener {


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

    var isReceiptAddedToDb: Boolean = false


    val clearAllFieldsEvent = SingleLiveEvent<Void>()
    val scrollUpEvent = SingleLiveEvent<Void>()

    init {
        if (receipt?.value == null) {
            receipt?.value = Receipt()
        }
        if (equipment?.value == null) {
            equipment?.value = Equipment()
        }
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
        // checking if it's first add, then we add receipt
        if (receipt?.value?.equipments == null || receipt?.value?.equipments?.isEmpty() == true) {
            // add receipt to the DB, save id to the mutable data and then add equipment to the DB
            equipment?.value?.let { receipt?.value?.equipments?.add(it) }

            receipt?.value?.let {
                receiptRepository.insertReceipt(ReceiptMapper.parse(it))
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .subscribe { id ->

                        isReceiptAddedToDb = true

                        receipt?.value?.id = id

                        saveEquipment(true)


                    }.apply { compositeDisposable.add(this) }
            }
        } else {
            // add equipment
            equipment?.value?.let { receipt?.value?.equipments?.add(it) }
            saveEquipment(true)
        }
    }

    private fun saveEquipment(addNextEquipment: Boolean) {
        equipment?.value?.let {
            receiptRepository.insertEquipment(EquipmentMapper.parse(it))
                .compose(schedulerProvider.ioToMainCompletableScheduler())
                .subscribe {
                    if (addNextEquipment) {
                        clearAllFieldsEvent.call()
                        scrollUpEvent.call()
                    } else {
                        recNavigator?.get()?.navigate(R.id.action_addReceiptEquipmentFragment_to_receivedFragment)
                    }
                }.apply { compositeDisposable.add(this) }
        }
    }


    @Suppress("UNUSED_PARAMETER")
    fun finishAddingEquipment(view: View) {

        equipment?.value?.let { receipt?.value?.equipments?.add(it) }


        if (!isReceiptAddedToDb) {
            receipt?.value?.let {
                receiptRepository.insertReceipt(ReceiptMapper.parse(it))
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .subscribe { id ->

                        isReceiptAddedToDb = true

                        receipt?.value?.id = id

                        saveEquipment(false)

                    }.apply { compositeDisposable.add(this) }
            }
        } else {

            saveEquipment(false)

        }
    }

}