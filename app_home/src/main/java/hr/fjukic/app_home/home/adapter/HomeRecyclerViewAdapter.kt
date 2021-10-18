package hr.fjukic.app_home.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.fjukic.app_home.home.model.HomeCardUI
import hr.fjukic.app_home.home.utils.HomeScreenElementType
import hr.fjukic.app_home.home.viewholder.HomeCardViewHolder
import hr.fjukic.app_home.home.viewholder.HomeManagementCardViewHolder

class HomeRecyclerViewAdapter(private val homeCards: MutableList<HomeCardUI>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            HomeScreenElementType.HOME_CARD.layoutId -> HomeCardViewHolder(view)
            HomeScreenElementType.MANAGMENT_CARD.layoutId -> HomeManagementCardViewHolder(view)
            else -> throw ClassNotFoundException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeCardViewHolder -> {
                holder.bind(homeCards[position])
            }
            is HomeManagementCardViewHolder -> {
                holder.bind(homeCards[position])
            }
            else -> throw ClassNotFoundException()
        }
    }

    override fun getItemViewType(position: Int): Int = homeCards[position].elementType.layoutId

    override fun getItemCount(): Int = homeCards.count()

    @SuppressLint("NotifyDataSetChanged")
    fun setupData(data: MutableList<HomeCardUI>){
        homeCards.clear()
        homeCards.addAll(data)
        notifyDataSetChanged()
    }
}