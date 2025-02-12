package uz.mobilesoft.cleanarchitecture.domain.usecase.impl

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.ExecuteForgotPasswordUseCase
import uz.mobilesoft.cleanarchitecture.domain.validator.UserValidator

class ExecuteForgotPasswordUseCaseImpl(
    private val authRepository: AuthRepository
) : ExecuteForgotPasswordUseCase {
    override fun invoke(phoneNumber: String): AuthResult {
        val isValidPhoneNumber = UserValidator.isValidPhoneNumber(phoneNumber)
        if (!isValidPhoneNumber) return AuthResult.PhoneNumberError

        val result = authRepository.forgotPassword(phoneNumber)
        return if (result) AuthResult.Success
        else AuthResult.Error
    }
}