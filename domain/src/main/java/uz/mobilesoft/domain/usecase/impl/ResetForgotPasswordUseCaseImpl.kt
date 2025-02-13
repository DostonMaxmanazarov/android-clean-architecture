package uz.mobilesoft.domain.usecase.impl

import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.ResetForgotPasswordUseCase
import uz.mobilesoft.domain.validator.UserValidator

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