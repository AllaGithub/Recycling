package ua.com.eco_lab.recycling.listener

import androidx.recyclerview.widget.RecyclerView

interface GenericListener<T> {

    fun onItemSelected(adapter: RecyclerView.Adapter<*>, value: T, position: Int)
}