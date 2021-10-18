package hr.fjukic.app_home.home.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hr.fjukic.app_home.R
import hr.fjukic.app_home.databinding.ItemHomeCardBinding
import hr.fjukic.app_home.home.model.HomeCardUI

class HomeCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var binding: ItemHomeCardBinding = ItemHomeCardBinding.bind(view)

    fun bind(homeCardUI: HomeCardUI) {
        binding.tvTitle.text = homeCardUI.title
        binding.tvDescription.text = homeCardUI.description
        homeCardUI.drawableId?.let {
            binding.ivCard.setImageResource(it)
        }
    }
}