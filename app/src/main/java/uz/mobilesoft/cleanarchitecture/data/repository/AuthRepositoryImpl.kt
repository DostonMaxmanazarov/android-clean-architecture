package uz.mobilesoft.cleanarchitecture.data.repository

import uz.mobilesoft.cleanarchitecture.data.models.mapToAuthRequest
import uz.mobilesoft.cleanarchitecture.data.storage.AuthStorageSharedPref
import uz.mobilesoft.cleanarchitecture.domain.models.LoginParam
import uz.mobilesoft.cleanarchitecture.domain.models.NewPasswordParam
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

        val formattedPhoneNumber = param.phoneNumber.formatPhoneNumber()

        val isEqualPassword = param.password == auth.password
        val isEqualPhoneNumber = formattedPhoneNumber == auth.phoneNumber

        return isEqualPhoneNumber && isEqualPassword
    }

    /**
     * Registers a new user if the given credentials do not already exist in storage.
     * */
    override fun registration(registrationParam: RegistrationParam): Boolean {
        val auth = authStorage.getAuth()

        val formattedPhoneNumber = registrationParam.phoneNumber.formatPhoneNumber()

        val isEqualPassword = registrationParam.password == auth.password
        val isEqualPhoneNumber = formattedPhoneNumber == auth.phoneNumber

        if (isEqualPassword && isEqualPhoneNumber) return false

        val cleanedRegParam = registrationParam.copy(phoneNumber = formattedPhoneNumber)
        val authRequest = cleanedRegParam.mapToAuthRequest()

        return authStorage.saveAuth(authRequest)
    }

    override fun forgotPassword(phoneNumber: String): Boolean {
        val auth = authStorage.getAuth()

        val formattedPhoneNumber = phoneNumber.formatPhoneNumber()

        val isEqualPhoneNumber = formattedPhoneNumber == auth.phoneNumber

        return isEqualPhoneNumber
    }

    override fun verifyOtp(code: String): Boolean {
        return true
    }

    override fun saveNewPassword(password: String): Boolean {
        val result = authStorage.saveNewPassword(password)

        return result
    }


    private fun String.formatPhoneNumber() = filter { it.isDigit() }
}