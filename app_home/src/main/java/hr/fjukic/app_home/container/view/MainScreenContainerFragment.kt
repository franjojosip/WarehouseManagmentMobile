package hr.fjukic.app_home.container.view

import hr.fjukic.app_common.view.AppFragment
import hr.fjukic.app_home.R
import hr.fjukic.app_home.container.viewmodel.MainScreenContainerVM
import hr.fjukic.app_home.databinding.FragmentMainScreenContainerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenContainerFragment :
    AppFragment<MainScreenContainerVM, FragmentMainScreenContainerBinding>() {
    override val layoutId: Int = R.layout.fragment_main_screen_container
    override val viewModel: MainScreenContainerVM by viewModel()
}