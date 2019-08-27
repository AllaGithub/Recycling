package ua.com.eco_lab.recycling.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import ua.com.eco_lab.recycling.BR
import java.util.*


class Receipt: BaseObservable() {

    @SerializedName("id")
    var id: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @SerializedName("donorName")
    var donorName: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.donorName)
        }

    @SerializedName("dateReceived")
    var dateReceived: Date? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateReceived)
        }

    @SerializedName("equipments")
    var equipments: MutableList<Equipment>? = arrayListOf()
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.equipments)
        }
}