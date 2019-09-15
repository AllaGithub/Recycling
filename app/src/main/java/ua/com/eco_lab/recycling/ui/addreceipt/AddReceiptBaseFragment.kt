package ua.com.eco_lab.recycling.ui.addreceipt

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import ua.com.eco_lab.recycling.ui.BaseFragment

abstract class AddReceiptBaseFragment : BaseFragment() {

    protected var addReceiptViewModel: AddReceiptViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            addReceiptViewModel = ViewModelProviders.of(this).get(AddReceiptViewModel::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        addReceiptViewModel?.setNavigator(this)
    }

    override fun onPause() {
        super.onPause()
        addReceiptViewModel?.removeNavigator(this)
    }
}