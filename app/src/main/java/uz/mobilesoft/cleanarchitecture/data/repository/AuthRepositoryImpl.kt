package uz.mobilesoft.cleanarchitecture.data.repository

import uz.mobilesoft.cleanarchitecture.data.models.mapToAuthRequest
import uz.mobilesoft.cleanarchitecture.data.storage.AuthStorageSharedPref
import uz.mobilesoft.cleanarchitecture.domain.models.LoginParam
import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository

/**
 * Implementation of `AuthRepository` interface.
 *
 * @param authStorage - Shared Preferences-based storage that stores user authentication data
 * */
class AuthRepositoryImpl(
    private val authStorage: AuthStorageSharedPref,
) : AuthRepository {

    /**
     * Checks if the provided login credentials match the stored authentication data.
     * */
    override fun login(param: LoginParam): Boolean {
        val auth = authStorage.getAuth()
        val isEqualPassword = param.password == auth.password
        val isEqualPhoneNumber = param.phoneNumber == auth.phoneNumber
        return isEqualPhoneNumber && isEqualPassword
    }

    /**
     * Registers a new user if the given credentials do not already exist in storage.
     * */
    override fun registration(param: RegistrationParam): Boolean {
        val auth = authStorage.getAuth()

        val isEqualPassword = param.password == auth.password
        val isEqualPhoneNumber = param.phoneNumber == auth.phoneNumber
        if (isEqualPassword && isEqualPhoneNumber) return false

        val authRequest = param.mapToAuthRequest()
        return authStorage.saveAuth(authRequest)
    }
}