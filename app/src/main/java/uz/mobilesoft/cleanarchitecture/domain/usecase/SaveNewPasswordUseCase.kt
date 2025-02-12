package uz.mobilesoft.cleanarchitecture.domain.usecase

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.models.NewPasswordParam

/**
 * SaveNewPasswordUseCase interface saves new password.
 * */
interface SaveNewPasswordUseCase {

    /**
     * @param param contains of new password and confirm password.
     *
     * @return indicating success or specific errors.
     * */
    operator fun invoke(param: NewPasswordParam): AuthResult
}