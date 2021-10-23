package hr.fjukic.app_home.container.navigation

import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import hr.fjukic.app_common.router.NavigationController
import hr.fjukic.app_common.view.AppFragment
import hr.fjukic.app_home.R

open class BottomNavigationComponentController(private val fragment: AppFragment<*, *>) :
    NavigationController {
    override val activityNavController = fragment.requireActivity().findNavController(R.id.bottomNavFragmentContainerView)
    override val fragmentNavController = fragment.findNavController()
}