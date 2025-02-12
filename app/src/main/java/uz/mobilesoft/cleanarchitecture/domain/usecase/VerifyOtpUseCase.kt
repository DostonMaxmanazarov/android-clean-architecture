package uz.mobilesoft.cleanarchitecture.domain.usecase

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult

interface VerifyOtpUseCase {
    operator fun invoke(otp: String): AuthResult
}