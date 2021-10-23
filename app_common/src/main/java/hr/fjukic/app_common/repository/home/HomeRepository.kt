package hr.fjukic.app_common.repository.home

import hr.fjukic.app_common.model.response.Home
import hr.fjukic.app_common.model.response.MainApiResponse
import io.reactivex.rxjava3.core.Observable

interface HomeRepository {
    fun getData(): Observable<MainApiResponse<Home>>
}