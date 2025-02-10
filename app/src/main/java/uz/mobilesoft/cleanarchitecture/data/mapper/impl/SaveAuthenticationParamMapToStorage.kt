package uz.mobilesoft.cleanarchitecture.data.mapper.impl

import uz.mobilesoft.cleanarchitecture.data.mapper.SingleMapper
import uz.mobilesoft.cleanarchitecture.data.models.AuthRequest
import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam

class SaveAuthenticationParamMapToStorage : SingleMapper<RegistrationParam, AuthRequest> {
    override fun invoke(value: RegistrationParam): AuthRequest {
        return AuthRequest(
            email = value.email,
            password = value.password,
            phoneNumber = value.phoneNumber,
        )
    }
}