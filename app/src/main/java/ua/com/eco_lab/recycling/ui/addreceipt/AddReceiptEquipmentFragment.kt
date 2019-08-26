package ua.com.eco_lab.recycling.ui.addreceipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.add_receipt_equipment_fragment.*
import ua.com.eco_lab.recycling.databinding.AddReceiptEquipmentFragmentBinding
import ua.com.eco_lab.recycling.ui.BaseFragment

class AddReceiptEquipmentFragment : BaseFragment() {


    private lateinit var binding: AddReceiptEquipmentFragmentBinding

    private lateinit var addReceiptViewModel: AddReceiptViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AddReceiptEquipmentFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addReceiptViewModel = ViewModelProviders.of(this).get(AddReceiptViewModel::class.java)

        binding.vm = addReceiptViewModel

        addReceiptViewModel.scrollUpEvent.observe(viewLifecycleOwner, Observer {
            scroll_view_add_eq.fullScroll(ScrollView.FOCUS_UP)
        })

        addReceiptViewModel.clearAllFieldsEvent.observe(viewLifecycleOwner, Observer {
            et_eq_name.setText("")
            et_id.setText("")
            et_id_inner.setText("")
            et_weight.setText("")
            et_missing.setText("")
            et_existing.setText("")
            et_comments.setText("")
        })

    }

    override fun onResume() {
        super.onResume()
        addReceiptViewModel.setNavigator(this)
    }

    override fun onPause() {
        super.onPause()
        addReceiptViewModel.removeNavigator(this)
    }
}