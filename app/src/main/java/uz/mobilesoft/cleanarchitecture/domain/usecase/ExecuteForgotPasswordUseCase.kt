package uz.mobilesoft.cleanarchitecture.domain.usecase

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult

interface ExecuteForgotPasswordUseCase {

    operator fun invoke(phoneNumber: String): AuthResult
}