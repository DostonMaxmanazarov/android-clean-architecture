package uz.mobilesoft.domain.usecase.impl

import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.models.LoginParam
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.ExecuteLoginUseCase
import uz.mobilesoft.domain.validator.UserValidator

/**
 * Implementation of `LoginUseCase` interface.
 * */
class ExecuteLoginUseCaseImpl(
    private val authRepository: AuthRepository
) : ExecuteLoginUseCase {
    override fun invoke(param: LoginParam): AuthResult {
        val isValidPhoneNumber = UserValidator.isValidPhoneNumber(param.phoneNumber)
        if (!isValidPhoneNumber) return AuthResult.PhoneNumberError

        val isValidPassword = UserValidator.isValidPassword(param.password)
        if (!isValidPassword) return AuthResult.PasswordError

        val result = authRepository.login(param = param)

        return if (result) AuthResult.Success
        else AuthResult.Error
    }
}