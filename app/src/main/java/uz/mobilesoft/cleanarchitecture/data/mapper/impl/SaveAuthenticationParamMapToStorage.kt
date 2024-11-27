package uz.mobilesoft.cleanarchitecture.data.mapper.impl

import uz.mobilesoft.cleanarchitecture.data.mapper.SingleMapper
import uz.mobilesoft.cleanarchitecture.data.storage.models.AuthenticationRequest
import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam

class SaveAuthenticationParamMapToStorage : SingleMapper<RegistrationParam, AuthenticationRequest> {
    override fun invoke(value: RegistrationParam): AuthenticationRequest {
        return AuthenticationRequest(
            email = value.email,
            password = value.password,
            phoneNumber = value.phoneNumber,
        )
    }
}