package hr.fjukic.app_common.repository.user

import hr.fjukic.app_common.model.response.User

interface UserRepository {
    fun getUser(): User?
}