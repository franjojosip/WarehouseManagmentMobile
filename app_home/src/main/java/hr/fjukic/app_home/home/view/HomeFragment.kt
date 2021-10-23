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
    private lateinit var homeCardsAdapter: HomeRecyclerViewAdapter
    private lateinit var managementTitlesAdapter: HomeRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeCardsAdapter = HomeRecyclerViewAdapter(mutableListOf())
        managementTitlesAdapter = HomeRecyclerViewAdapter(mutableListOf())

        viewModel.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupHomeCardsRecyclerView()
        setupHomeManagementCardsRecyclerView()
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
        binding?.recyclerViewManagement?.adapter = managementTitlesAdapter
        binding?.recyclerViewManagement?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setupHomeCardsRecyclerView() {
        binding?.recyclerView?.adapter = homeCardsAdapter
        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding?.recyclerView)
        binding?.recyclerView?.scrollToPosition(1)
    }

    override fun showLoader() {
        binding?.loaderLayout?.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        binding?.loaderLayout?.visibility = View.GONE
    }
}