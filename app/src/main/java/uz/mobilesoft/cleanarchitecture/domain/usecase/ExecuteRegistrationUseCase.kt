package uz.mobilesoft.cleanarchitecture.domain.usecase

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam

/**
 * Use case for performing user registration.
 **/
interface ExecuteRegistrationUseCase {
    /**
     *
     * @param param registration parameters, including phone number, password and passwordConfirmation.
     * @return indicating success or failure.
     * */
    operator fun invoke(param: RegistrationParam): AuthResult
}