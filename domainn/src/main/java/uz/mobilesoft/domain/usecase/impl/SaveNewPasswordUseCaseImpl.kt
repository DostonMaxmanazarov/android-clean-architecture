package uz.mobilesoft.domain.usecase.impl

import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.models.NewPasswordParam
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.SaveNewPasswordUseCase
import uz.mobilesoft.domain.validator.UserValidator

class SaveNewPasswordUseCaseImpl(
    private val authRepository: AuthRepository
) : SaveNewPasswordUseCase {
    override fun invoke(param: NewPasswordParam): AuthResult {
        val isValidPassword = UserValidator.isValidPassword(param.password)
        if (!isValidPassword) return AuthResult.PasswordError

        val isEqualPassword =
            UserValidator.isValidConfirmPassword(param.password, param.passwordConfirm)
        if (!isEqualPassword) return AuthResult.PasswordConfirmError

        val result = authRepository.saveNewPassword(param.password)
        return if (result) AuthResult.Success
        else AuthResult.Error
    }
}