package ua.com.eco_lab.recycling.ui.received

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ua.com.eco_lab.recycling.R

class ReceivedFragment : Fragment() {

    private lateinit var receivedViewModel: ReceivedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        receivedViewModel =
            ViewModelProviders.of(this).get(ReceivedViewModel::class.java)
        val root = inflater.inflate(R.layout.received_fragment, container, false)

        return root
    }
}