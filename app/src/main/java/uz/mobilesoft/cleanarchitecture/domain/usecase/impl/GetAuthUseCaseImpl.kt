package uz.mobilesoft.cleanarchitecture.domain.usecase.impl

import uz.mobilesoft.cleanarchitecture.domain.models.LoginParam
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.GetAuthUseCase

class GetAuthUseCaseImpl(
    private val authRepository: AuthRepository
) : GetAuthUseCase {
    override fun invoke(param: LoginParam): Boolean {
        val authentication = authRepository.getAuthentication()
        if (param.password == authentication.password && param.phoneNumber == authentication.phoneNumber) return true
        return false
    }
}