package uz.mobilesoft.cleanarchitecture.domain.usecase

import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam

interface SaveAuthUseCase {
    operator fun invoke(param: RegistrationParam): Boolean
}