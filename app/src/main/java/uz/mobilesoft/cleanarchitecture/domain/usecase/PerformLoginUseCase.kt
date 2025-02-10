package uz.mobilesoft.cleanarchitecture.domain.usecase

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.models.LoginParam

/**
 * Use case for handling user login.
 * */
interface PerformLoginUseCase {

    /**
     * @param param login parameters, including phone number and password.
     * @return indicating success or failure.
     * */
    operator fun invoke(param: LoginParam): AuthResult
}