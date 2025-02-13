package uz.mobilesoft.cleanarchitecture.data.storage

import uz.mobilesoft.cleanarchitecture.data.models.AuthRequest

/**
 * AuthStorageSharedPref Interface saves user authentication data.
 *
 * Using a shared preference-based storage or any other persistent mechanism.
 */
interface AuthStorageSharedPref {
    /**
     * Saves the given authentication data.
     *
     * @param request The authentication data to be stored.
     * @return `true` if the data is successfully saved, `false` otherwise.
     * */
    fun saveAuth(request: AuthRequest): Boolean

    /**
     * Retrieves the stored authentication data.
     *
     * @return `The default values` depend on the implementation of AuthRequest
     * if no authentication data is found.
     * */
    fun getAuth(): AuthRequest

    fun saveNewPassword(password: String): Boolean
}