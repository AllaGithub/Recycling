package ua.com.eco_lab.recycling.ui.turnover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ua.com.eco_lab.recycling.R

class TurnoverFragment : Fragment() {

    private lateinit var turnoverViewModel: TurnoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        turnoverViewModel =
            ViewModelProviders.of(this).get(TurnoverViewModel::class.java)
        val root = inflater.inflate(R.layout.turnover_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        turnoverViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}