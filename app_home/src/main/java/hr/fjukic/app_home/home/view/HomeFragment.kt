package hr.fjukic.app_home.home.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import hr.fjukic.app_common.view.AppFragment
import hr.fjukic.app_home.R
import hr.fjukic.app_home.databinding.FragmentHomeBinding
import hr.fjukic.app_home.home.adapter.HomeRecyclerViewAdapter
import hr.fjukic.app_home.home.viewmodel.HomeVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : AppFragment<HomeVM, FragmentHomeBinding>() {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeVM by viewModel()
    private val homeCardsAdapter by lazy { HomeRecyclerViewAdapter(mutableListOf()) }
    private val managementTitlesAdapter by lazy { HomeRecyclerViewAdapter(mutableListOf()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupHomeCardsRecyclerView()
        setupHomeManagementCardsRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
    }

    private fun setupObservers() {
        viewModel.screenAdapter.homeCards.observeWithNotNull {
            homeCardsAdapter.setupData(it)
        }
        viewModel.screenAdapter.managementTitles.observeWithNotNull {
            managementTitlesAdapter.setupData(it)
        }
    }

    private fun setupHomeManagementCardsRecyclerView() {
    }

    private fun setupHomeCardsRecyclerView() {
        binding?.recyclerView?.adapter = homeCardsAdapter
        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding?.recyclerView)
        binding?.recyclerView?.scrollToPosition(1)
    }
}