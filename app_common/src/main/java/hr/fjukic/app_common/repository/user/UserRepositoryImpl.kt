package hr.fjukic.app_common.repository.user

import hr.fjukic.app_common.model.response.User
import hr.fjukic.app_common.sharedpref.AppSharedPreference

class UserRepositoryImpl(private val sharedPreference: AppSharedPreference): UserRepository {
    override fun getUser(): User? = sharedPreference.user
}