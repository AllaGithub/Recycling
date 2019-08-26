package ua.com.eco_lab.recycling.ui.addreceipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import ua.com.eco_lab.recycling.databinding.AddReceiptFragmentBinding
import ua.com.eco_lab.recycling.extensions.hideKeyboard
import ua.com.eco_lab.recycling.ui.BaseFragment

class AddReceiptFragment : BaseFragment() {

    private lateinit var binding: AddReceiptFragmentBinding

    private lateinit var addReceiptViewModel: AddReceiptViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AddReceiptFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addReceiptViewModel = ViewModelProviders.of(this).get(AddReceiptViewModel::class.java)

        binding.vm = addReceiptViewModel

    }


    fun setDate(view: View) {
        view.hideKeyboard()
        addReceiptViewModel.setDate(context!!)
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