package ua.com.eco_lab.recycling.ui.received

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.received_fragment.*
import ua.com.eco_lab.recycling.adapter.ReceiptAdapter
import ua.com.eco_lab.recycling.databinding.ReceivedFragmentBinding
import ua.com.eco_lab.recycling.listener.GenericListener
import ua.com.eco_lab.recycling.model.Receipt
import ua.com.eco_lab.recycling.ui.BaseFragment

class ReceivedFragment : BaseFragment(), GenericListener<Receipt> {

    private lateinit var binding: ReceivedFragmentBinding

    private lateinit var receivedViewModel: ReceivedViewModel

    private lateinit var adapter: ReceiptAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ReceivedFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        receivedViewModel = ViewModelProviders.of(this).get(ReceivedViewModel::class.java)

        binding.vm = receivedViewModel

        receivedViewModel.receiptList?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    override fun onResume() {
        super.onResume()
        receivedViewModel.setNavigator(this)
        receivedViewModel.refreshData()
    }

    override fun onPause() {
        super.onPause()
        receivedViewModel.removeNavigator(this)
    }

    override fun onItemSelected(adapter: RecyclerView.Adapter<*>, value: Receipt, position: Int) {
        value.id?.let {
            findNavController().navigate(ReceivedFragmentDirections.actionReceivedFragmentToEquipmentFragment(it))
        }
    }

    private fun setupAdapter() {
        adapter = ReceiptAdapter(this)
        receipt_recycler.adapter = adapter
    }
}