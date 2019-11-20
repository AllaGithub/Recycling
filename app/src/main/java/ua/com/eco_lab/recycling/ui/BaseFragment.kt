package ua.com.eco_lab.recycling.ui

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import ua.com.eco_lab.recycling.RecyclingNavigator

open class BaseFragment : Fragment(), RecyclingNavigator {

    override fun navigate(destination: NavDirections) {
        findNavController().navigate(destination)
    }

    override fun navigate(destinationId: Int) {
        findNavController().navigate(destinationId)
    }

}