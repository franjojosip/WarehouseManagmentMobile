package hr.fjukic.app_common.sharedpref

import hr.fjukic.app_common.model.response.User

interface AppSharedPreference {
    var appVersion : String
    var apiURL : String
    var appLocale: String
    var authToken: String?
    var refreshToken: String?
    var user: User?
}