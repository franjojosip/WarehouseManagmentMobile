package hr.fjukic.app_home.home.utils

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class HomeCardsPageTransformation : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val offset = position * - 50
        page.translationX = offset
        page.setPadding(120, 0 , 120,0)
    }
}