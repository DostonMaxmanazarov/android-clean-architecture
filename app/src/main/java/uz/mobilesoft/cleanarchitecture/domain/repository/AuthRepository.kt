package uz.mobilesoft.cleanarchitecture.domain.repository

import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam
import uz.mobilesoft.cleanarchitecture.domain.models.LoginParam
import uz.mobilesoft.cleanarchitecture.domain.models.NewPasswordParam

/**
 * Interface for user authentication repository.
 * */
interface AuthRepository {
    /**
     * Checks the user for login.
     *
     * @param param Login parameters(phone number and password).
     * @return - `true` if the login information is correct, otherwise `false`.
     * */
    fun login(param: LoginParam): Boolean

    /**
     * User registration.
     *
     * @param param Registration parameters(email, phone number, password).
     * @return - `true` if the user was successfully registered, `false` if the user already exists.
     **/
    fun registration(registrationParam: RegistrationParam): Boolean

    /**
     * Recovery of forgotten password. The registered number can reset the password
     *
     * @param phoneNumber user phone number
     * @return `true` if the user was successfully registered, otherwise `false`.
     * */
    fun forgotPassword(phoneNumber: String): Boolean

    /**
     * otp verification
     *
     * @param code is otp code
     * @return `true` if the otp code is correct, otherwise `false`.
     * */
    fun verifyOtp(code: String): Boolean

    /**
     * save the newly entered password
     *
     * @param password is new password
     * @return `true` if the password was successfully saved, otherwise `false`.
     * */
    fun saveNewPassword(password: String): Boolean
}