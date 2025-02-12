package uz.mobilesoft.domain.usecase

import uz.mobilesoft.domain.models.params.LoginParam

interface GetAuthUseCase {
    operator fun invoke(param: LoginParam): Boolean
}