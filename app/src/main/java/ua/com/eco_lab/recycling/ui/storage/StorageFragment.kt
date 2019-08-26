package ua.com.eco_lab.recycling.ui.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ua.com.eco_lab.recycling.R

class StorageFragment : Fragment() {

    private lateinit var storageViewModel: StorageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        storageViewModel =
            ViewModelProviders.of(this).get(StorageViewModel::class.java)
        val root = inflater.inflate(R.layout.storage_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        storageViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }
}