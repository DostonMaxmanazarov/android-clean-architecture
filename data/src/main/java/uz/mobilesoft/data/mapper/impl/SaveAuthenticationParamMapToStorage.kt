package uz.mobilesoft.data.mapper.impl

import uz.mobilesoft.data.mapper.SingleMapper
import uz.mobilesoft.data.storage.models.AuthenticationRequest
import uz.mobilesoft.domain.models.params.RegistrationParam

class SaveAuthenticationParamMapToStorage : SingleMapper<RegistrationParam, AuthenticationRequest> {
    override fun invoke(value: RegistrationParam): AuthenticationRequest {
        return AuthenticationRequest(
            email = value.email,
            password = value.password,
            phoneNumber = value.phoneNumber,
        )
    }
}