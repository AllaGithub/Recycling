package ua.com.eco_lab.recycling

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

interface RecyclingNavigator {

    fun navigate(@IdRes destinationId: Int)
    fun navigate(destination: NavDirections)
}