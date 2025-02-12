package uz.mobilesoft.domain.usecase

import uz.mobilesoft.domain.models.AuthResult

/**
 * VerifyOtpUseCase interface verifies the entered OTP code.
 * */
interface VerifyOtpUseCase {

    /**
     * @param code is otp code.
     *
     * @return indicating success or specific errors.
     * */
    operator fun invoke(code: String): AuthResult
}