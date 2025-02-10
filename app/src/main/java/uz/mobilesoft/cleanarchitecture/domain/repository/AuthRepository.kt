package uz.mobilesoft.cleanarchitecture.domain.repository

import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam
import uz.mobilesoft.cleanarchitecture.domain.models.LoginParam

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
    fun registration(param: RegistrationParam): Boolean
}