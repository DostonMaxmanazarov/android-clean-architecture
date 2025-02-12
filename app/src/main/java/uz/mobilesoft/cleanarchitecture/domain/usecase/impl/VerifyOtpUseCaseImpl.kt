package uz.mobilesoft.cleanarchitecture.domain.usecase.impl

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.VerifyOtpUseCase

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
