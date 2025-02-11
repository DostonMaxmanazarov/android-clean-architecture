package uz.mobilesoft.cleanarchitecture.data.storage.impl

import android.content.Context
import uz.mobilesoft.cleanarchitecture.data.storage.AuthStorageSharedPref
import uz.mobilesoft.cleanarchitecture.data.models.AuthRequest

/**
 * Implementation of `AuthStorageSharedPref` interface.
 * */
class AuthStorageSharedPrefImpl(
    context: Context
) : AuthStorageSharedPref {
    private val sharedPreference =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveAuth(request: AuthRequest): Boolean {
        sharedPreference.edit().putString(PASSWORD, request.password).apply()
        sharedPreference.edit().putString(PHONE_NUMBER, request.phoneNumber).apply()
        return true
    }

    override fun getAuth(): AuthRequest {
        val password = sharedPreference.getString(PASSWORD, DEFAULT_PASSWORD) ?: DEFAULT_PASSWORD
        val phoneNumber =
            sharedPreference.getString(PHONE_NUMBER, DEFAULT_PHONE_NUMBER) ?: DEFAULT_PHONE_NUMBER

        return AuthRequest(password = password, phoneNumber = phoneNumber)
    }

    private companion object {
        const val PASSWORD = "password"
        const val PHONE_NUMBER = "phone_number"
        const val DEFAULT_PASSWORD = "empty password"
        const val DEFAULT_PHONE_NUMBER = "empty phone number"
        const val SHARED_PREFS_NAME = "shared_prefs_name"
    }
}