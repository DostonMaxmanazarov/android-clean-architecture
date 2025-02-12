package uz.mobilesoft.cleanarchitecture.domain.usecase.impl

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.ResetForgotPasswordUseCase
import uz.mobilesoft.cleanarchitecture.domain.validator.UserValidator

class ResetForgotPasswordUseCaseImpl(
    private val authRepository: AuthRepository
) : ResetForgotPasswordUseCase {
    override fun invoke(phoneNumber: String): AuthResult {
        val isValidPhoneNumber = UserValidator.isValidPhoneNumber(phoneNumber)
        if (!isValidPhoneNumber) return AuthResult.PhoneNumberError

        val result = authRepository.forgotPassword(phoneNumber)
        return if (result) AuthResult.Success
        else AuthResult.Error
    }
}