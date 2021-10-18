package hr.fjukic.app_home.home.model

import hr.fjukic.app_home.home.utils.HomeScreenElementType

data class HomeCardUI(
    val title: String,
    val description: String = "",
    val drawableId: Int? = null,
    val elementType: HomeScreenElementType = HomeScreenElementType.HOME_CARD
)
