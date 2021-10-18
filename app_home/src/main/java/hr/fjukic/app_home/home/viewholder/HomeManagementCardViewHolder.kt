package hr.fjukic.app_home.home.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hr.fjukic.app_home.databinding.ItemManagmentCardBinding
import hr.fjukic.app_home.home.model.HomeCardUI

class HomeManagementCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var binding: ItemManagmentCardBinding = ItemManagmentCardBinding.bind(view)

    fun bind(homeCardUI: HomeCardUI) {
        binding.tvTitle.text = homeCardUI.title
    }
}