package uz.mobilesoft.cleanarchitecture.domain.usecase.impl

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.VerifyOtpUseCase

class VerifyOtpUseCaseImpl(
    private val authRepository: AuthRepository
) : VerifyOtpUseCase {
    override fun invoke(otp: String): AuthResult {
        if (otp.isEmpty()) return AuthResult.OtpError

        val result = authRepository.verifyOtp(otp)
        return if (result) AuthResult.Success
        else AuthResult.Error
    }
}
