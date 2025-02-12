package uz.mobilesoft.cleanarchitecture.domain.usecase

import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.models.NewPasswordParam

interface SaveNewPasswordUseCase {
    operator fun invoke(param: NewPasswordParam): AuthResult
}