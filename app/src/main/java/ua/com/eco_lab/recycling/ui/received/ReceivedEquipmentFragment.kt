package ua.com.eco_lab.recycling.ui.received

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.received_equipment_fragment.*
import ua.com.eco_lab.recycling.adapter.EquipmentAdapter
import ua.com.eco_lab.recycling.databinding.ReceivedEquipmentFragmentBinding
import ua.com.eco_lab.recycling.listener.GenericListener
import ua.com.eco_lab.recycling.model.Equipment
import ua.com.eco_lab.recycling.ui.BaseFragment

class ReceivedEquipmentFragment : BaseFragment(), GenericListener<Equipment> {

    private lateinit var binding: ReceivedEquipmentFragmentBinding

    private lateinit var equipmentListViewModel: ReceivedEquipmentViewModel


    private lateinit var adapter: EquipmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ReceivedEquipmentFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        equipmentListViewModel = ViewModelProviders.of(this).get(ReceivedEquipmentViewModel::class.java)

        binding.vm = equipmentListViewModel

        arguments?.let {
            val args = ReceivedEquipmentFragmentArgs.fromBundle(it)
            equipmentListViewModel.receiptId?.value = args.receiptId
        }

        setupAdapter()

        equipmentListViewModel.equipmentList?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onResume() {
        super.onResume()
        equipmentListViewModel.setNavigator(this)
        equipmentListViewModel.refreshData()
    }

    override fun onPause() {
        super.onPause()
        equipmentListViewModel.removeNavigator(this)
    }

    override fun onItemSelected(adapter: RecyclerView.Adapter<*>, value: Equipment, position: Int) {
        //todo
    }

    private fun setupAdapter() {
        adapter = EquipmentAdapter(this)
        equipment_recycler.adapter = adapter
    }
}