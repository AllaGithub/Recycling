package ua.com.eco_lab.recycling.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import ua.com.eco_lab.recycling.BR
import java.util.*


class Receipt: BaseObservable() {

    @SerializedName("id")
    var id: Long? = null
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

    companion object {
        val diffCallback: DiffUtil.ItemCallback<Receipt> = object : DiffUtil.ItemCallback<Receipt>() {
            override fun areItemsTheSame(oldItem: Receipt, newItem: Receipt): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Receipt, newItem: Receipt): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}