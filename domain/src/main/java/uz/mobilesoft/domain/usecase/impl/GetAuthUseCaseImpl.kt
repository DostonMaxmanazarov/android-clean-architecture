package uz.mobilesoft.domain.usecase.impl

import uz.mobilesoft.domain.models.params.LoginParam
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.GetAuthUseCase

class GetAuthUseCaseImpl(
    private val authRepository: AuthRepository
) : GetAuthUseCase {
    override fun invoke(param: LoginParam): Boolean {
        val authentication = authRepository.getAuthentication()
        if (param.password == authentication.password && param.phoneNumber == authentication.phoneNumber) return true
        return false
    }
}