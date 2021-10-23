package hr.fjukic.app_home.container.view

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import hr.fjukic.app_common.router.NavDirectionsWrapper
import hr.fjukic.app_common.view.AppFragment
import hr.fjukic.app_home.R
import hr.fjukic.app_home.container.navigation.BottomNavigationComponentController
import hr.fjukic.app_home.container.viewmodel.MainScreenContainerVM
import hr.fjukic.app_home.databinding.FragmentMainScreenContainerBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainScreenContainerFragment :
    AppFragment<MainScreenContainerVM, FragmentMainScreenContainerBinding>() {
    override val layoutId: Int = R.layout.fragment_main_screen_container
    override val viewModel: MainScreenContainerVM by viewModel()
    override val navigationController: BottomNavigationComponentController by inject {
        parametersOf(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.screenAdapter.headerTitle.observeWithNotNull {
            binding?.headerProfile?.tvProfile?.text = it
        }
    }
}