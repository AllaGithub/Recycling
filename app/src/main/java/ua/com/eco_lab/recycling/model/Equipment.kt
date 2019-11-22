package ua.com.eco_lab.recycling.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ua.com.eco_lab.recycling.BR


class Equipment() : BaseObservable() {

    @SerializedName("id")
    @Expose
    var id: Long? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @SerializedName("inventoryId")
    @Expose
    var inventoryId: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.inventoryId)
        }


    @SerializedName("innerId")
    @Expose
    var innerId: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.innerId)
        }

    @SerializedName("name")
    @Expose
    var name: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @SerializedName("weight")
    @Expose
    var weight: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.weight)
        }

    @SerializedName("comments")
    @Expose
    var comments: String? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.comments)
        }

    @SerializedName("receiptId")
    @Expose
    var receiptId: Long? = null
        @Bindable get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.receiptId)
        }

    companion object {
        val diffCallback: DiffUtil.ItemCallback<Equipment> = object : DiffUtil.ItemCallback<Equipment>() {
            override fun areItemsTheSame(oldItem: Equipment, newItem: Equipment): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Equipment, newItem: Equipment): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}
