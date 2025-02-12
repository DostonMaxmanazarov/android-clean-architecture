package uz.mobilesoft.domain.usecase

import uz.mobilesoft.domain.models.params.RegistrationParam

interface SaveAuthUseCase {
    operator fun invoke(param: RegistrationParam): Boolean
}