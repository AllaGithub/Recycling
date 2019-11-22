package ua.com.eco_lab.recycling.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.com.eco_lab.recycling.databinding.RowReceiptEquipmentBinding
import ua.com.eco_lab.recycling.listener.GenericListener
import ua.com.eco_lab.recycling.model.Equipment

class EquipmentAdapter(private val equipmentListener: GenericListener<Equipment>) : ListAdapter<Equipment,
        EquipmentAdapter.ViewHolder>(Equipment.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(RowReceiptEquipmentBinding.inflate(inflater, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.binding.rootView.setOnClickListener {
            holder.binding.equipment?.let {
                equipmentListener.onItemSelected(this, it, holder.adapterPosition)
            }
        }
    }


    inner class ViewHolder(val binding: RowReceiptEquipmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Equipment) {
            binding.equipment = item
        }
    }

}