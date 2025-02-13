package uz.mobilesoft.domain.usecase.impl

import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.VerifyOtpUseCase

class VerifyOtpUseCaseImpl(
    private val authRepository: AuthRepository
) : VerifyOtpUseCase {
    override fun invoke(code: String): AuthResult {
        if (code.isEmpty()) return AuthResult.OtpError

        val result = authRepository.verifyOtp(code)
        return if (result) AuthResult.Success
        else AuthResult.Error
    }
}