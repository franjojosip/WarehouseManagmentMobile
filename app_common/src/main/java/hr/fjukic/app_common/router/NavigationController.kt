package hr.fjukic.app_common.router

import androidx.navigation.NavController
import hr.fjukic.app_common.router.NavDirectionsWrapper

interface NavigationController {
    val activityNavController: NavController
    val fragmentNavController: NavController

    fun navigate(navDirections: NavDirectionsWrapper) {
        val navController = getNavController(navDirections)
        navController.navigate(navDirections.navDirections)
    }

    private fun getNavController(navDirections: NavDirectionsWrapper): NavController {
        return when (navDirections.isNewScreen) {
            true -> activityNavController
            else -> fragmentNavController
        }
    }

    fun popFromStack(){
        when{
            fragmentNavController.previousBackStackEntry != null -> {
                fragmentNavController.popBackStack()
            }
            else -> activityNavController.popBackStack()
        }
    }
}