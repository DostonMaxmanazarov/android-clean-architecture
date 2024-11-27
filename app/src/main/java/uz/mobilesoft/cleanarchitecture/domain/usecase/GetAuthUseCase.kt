package uz.mobilesoft.cleanarchitecture.domain.usecase

import uz.mobilesoft.cleanarchitecture.domain.models.LoginParam

interface GetAuthUseCase {
    operator fun invoke(param: LoginParam): Boolean
}