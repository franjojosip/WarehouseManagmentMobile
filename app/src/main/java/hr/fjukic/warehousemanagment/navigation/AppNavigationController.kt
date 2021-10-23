package hr.fjukic.warehousemanagment.navigation

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import hr.fjukic.app_common.router.NavigationController
import hr.fjukic.app_common.view.AppFragment
import hr.fjukic.warehousemanagment.R

class AppNavigationController(fragment: AppFragment<*, *>) : NavigationController {
    override val activityNavController: NavController =
        Navigation.findNavController(fragment.requireActivity(), R.id.nav_host_fragment)

    override val fragmentNavController: NavController = fragment.findNavController()
}