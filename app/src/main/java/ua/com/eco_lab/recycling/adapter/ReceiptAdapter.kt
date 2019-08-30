package ua.com.eco_lab.recycling.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.com.eco_lab.recycling.databinding.RowReceiptBinding
import ua.com.eco_lab.recycling.listener.GenericListener
import ua.com.eco_lab.recycling.model.Receipt

class ReceiptAdapter(private val receiptListener: GenericListener<Receipt>) : ListAdapter<Receipt,
        ReceiptAdapter.ViewHolder>(Receipt.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(RowReceiptBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.binding.rootView.setOnClickListener {
            holder.binding.receipt?.let {
                receiptListener.onItemSelected(this, it, holder.adapterPosition)
            }
        }
    }

    inner class ViewHolder(val binding: RowReceiptBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Receipt) {
            binding.receipt = item
        }
    }
}