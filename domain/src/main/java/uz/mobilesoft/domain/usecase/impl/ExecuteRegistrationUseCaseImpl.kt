package uz.mobilesoft.domain.usecase.impl

import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.models.RegistrationParam
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.ExecuteRegistrationUseCase
import uz.mobilesoft.domain.validator.UserValidator

/**
 * Implementation of `PerformRegistrationUseCase` interface.
 * */
class ExecuteRegistrationUseCaseImpl(
    private val authRepository: AuthRepository
) : ExecuteRegistrationUseCase {

    override fun invoke(param: RegistrationParam): AuthResult {

        val isValidPhoneNumber = UserValidator.isValidPhoneNumber(param.phoneNumber)
        if (!isValidPhoneNumber) return AuthResult.PhoneNumberError

        val isValidPassword = UserValidator.isValidPassword(param.password)
        if (!isValidPassword) return AuthResult.PasswordError

        val isEqualPassword = UserValidator.isValidConfirmPassword(param.password, param.confirmPassword)
        if (!isEqualPassword) return AuthResult.PasswordConfirmError

        val result = authRepository.registration(registrationParam = param)
        return if (result) AuthResult.Success
        else AuthResult.Error
    }
}