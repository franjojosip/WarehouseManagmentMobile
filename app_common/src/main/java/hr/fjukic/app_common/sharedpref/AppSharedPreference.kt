package hr.fjukic.app_common.sharedpref

interface AppSharedPreference {
    var appVersion : String
    var apiURL : String
    var appLocale: String
    var authToken: String?
    var refreshToken: String?
}