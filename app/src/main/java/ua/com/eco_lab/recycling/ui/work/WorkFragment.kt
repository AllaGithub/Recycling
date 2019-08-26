package ua.com.eco_lab.recycling.ui.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ua.com.eco_lab.recycling.R

class WorkFragment : Fragment() {

    private lateinit var workViewModel: WorkViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        workViewModel =
            ViewModelProviders.of(this).get(WorkViewModel::class.java)
        val root = inflater.inflate(R.layout.work_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        workViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}