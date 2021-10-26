package hr.fjukic.app_single_page.city.view

import hr.fjukic.app_common.view.AppFragment
import hr.fjukic.app_single_page.R
import hr.fjukic.app_single_page.city.viewmodel.CityVM
import hr.fjukic.app_single_page.databinding.FragmentCityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityFragment : AppFragment<CityVM, FragmentCityBinding>() {
    override val layoutId: Int = R.layout.fragment_city
    override val viewModel: CityVM by viewModel()
}