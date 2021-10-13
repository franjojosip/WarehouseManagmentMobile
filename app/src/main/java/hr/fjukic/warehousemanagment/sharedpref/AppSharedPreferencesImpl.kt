package hr.fjukic.warehousemanagment.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import hr.fjukic.app_common.model.response.User
import hr.fjukic.app_common.sharedpref.AppSharedPreference

internal class AppSharedPreferencesImpl(applicationContext: Context, private val gson: Gson) :
    AppSharedPreference {
    private val defaultSharedPref: SharedPreferences

    init {
        val packageName = applicationContext.packageName
        defaultSharedPref = applicationContext.getSharedPreferences(
            "$packageName$DEFAULT_SHARED_PREF",
            Context.MODE_PRIVATE
        )
    }

    override var appVersion: String
        get() = defaultSharedPref.getString(KEY_APP_VERSION, "")!!
        set(value) {
            editor { putString(KEY_APP_VERSION, value) }
        }

    override var apiURL: String
        get() = defaultSharedPref.getString(KEY_API_URL, "")!!
        set(value) {
            editor { putString(KEY_API_URL, value) }
        }

    override var appLocale: String
        get() = defaultSharedPref.getString(KEY_LANGUAGE, "")!!
        set(value) {
            editor { putString(KEY_LANGUAGE, value) }
        }

    override var authToken: String?
        get() = defaultSharedPref.getString(KEY_REFRESH_TOKEN, "")
        set(value) {
            editor { putString(KEY_REFRESH_TOKEN, value) }
        }

    override var refreshToken: String?
        get() = defaultSharedPref.getString(KEY_REFRESH_TOKEN, "")
        set(value) {
            editor { putString(KEY_REFRESH_TOKEN, value) }
        }

    override var user: User?
        get() = gson.fromJson(defaultSharedPref.getString(KEY_USER, ""), User::class.java)
        set(value) {
            editor { putString(KEY_USER, gson.toJson(value)) }
        }


    protected fun editor(editor: SharedPreferences.Editor.() -> Unit) {
        defaultSharedPref.edit().also(editor).apply()
    }
}