package com.limapps.mitrueque2.preferences

import android.content.Context
import android.content.SharedPreferences

object Prefs {

    private lateinit var prefs: SharedPreferences
    private const val PREFS_FILENAME = "PreferencesApp"
    private const val MODE = Context.MODE_PRIVATE

    private const val USER_NAME = "name_user"
    private const val USER_LASTNAME = "lastname_user"
    private val IMAGE_PROFILE = "image_profile"
    private val USER_UID = "user_uid"
    private val USER_EMAIL = "user_email"
    private val USER_DATA = "user_data"
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_FILENAME, MODE)
    }

    var user_uid: String
        get() = prefs.getString(USER_UID, "default_user_uid")!!
        set(value) = prefs.edit().putString(USER_UID, value).apply()

    var user_name: String
        get() = prefs.getString(USER_NAME, "")!!
        set(value) = prefs.edit().putString(USER_NAME, value).apply()

    var user_data: String
        get() = prefs.getString(USER_DATA, "")!!
        set(value) = prefs.edit().putString(USER_DATA, value).apply()


}