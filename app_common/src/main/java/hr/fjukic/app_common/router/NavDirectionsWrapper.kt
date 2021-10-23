package hr.fjukic.app_common.router

import androidx.navigation.NavDirections

data class NavDirectionsWrapper(val navDirections: NavDirections, val isNewScreen: Boolean = false){
}