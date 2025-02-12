package uz.mobilesoft.cleanarchitecture.domain.usecase

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult

/**
 * ResetForgotPasswordUseCase interface handles the password reset process.
 * */
interface ResetForgotPasswordUseCase {

    /**
     * @param phoneNumber is user phone number.
     * A registered phone number is required to reset the password
     *
     * @return indicating success or specific errors.
     * */
    operator fun invoke(phoneNumber: String): AuthResult
}