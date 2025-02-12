package uz.mobilesoft.domain.usecase.impl

import uz.mobilesoft.domain.models.params.RegistrationParam
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.SaveAuthUseCase
import uz.mobilesoft.domain.utils.isValidationEmail
import uz.mobilesoft.domain.utils.isValidationPassword
import uz.mobilesoft.domain.utils.isValidationPhoneNumber

class SaveAuthUseCaseImpl(
    private val authRepository: AuthRepository
) : SaveAuthUseCase {

    override fun invoke(param: RegistrationParam): Boolean {
        val oldAuthenticationData = authRepository.getAuthentication()

        if (param.email.isValidationEmail() &&
            param.phoneNumber.isValidationPhoneNumber() &&
            param.password.isValidationPassword() &&
            param.password == param.confirmPassword) {

            if (oldAuthenticationData.phoneNumber == param.phoneNumber &&
                oldAuthenticationData.password == param.password) {
                return true
            }
            return authRepository.saveAuthentication(saveParam = param)
        }
        return false
    }
}