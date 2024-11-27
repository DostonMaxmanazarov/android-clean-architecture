package uz.mobilesoft.cleanarchitecture.domain.usecase.impl

import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.SaveAuthUseCase
import uz.mobilesoft.cleanarchitecture.domain.utils.isValidationEmail
import uz.mobilesoft.cleanarchitecture.domain.utils.isValidationPassword
import uz.mobilesoft.cleanarchitecture.domain.utils.isValidationPhoneNumber

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