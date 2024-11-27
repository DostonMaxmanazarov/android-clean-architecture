package uz.mobilesoft.cleanarchitecture.data.mapper.impl

import uz.mobilesoft.cleanarchitecture.data.mapper.SingleMapper
import uz.mobilesoft.cleanarchitecture.data.storage.models.AuthenticationRequest
import uz.mobilesoft.cleanarchitecture.domain.models.Authentication

class AuthenticationRequestMapToDomain :
    SingleMapper<AuthenticationRequest, Authentication> {
    override fun invoke(value: AuthenticationRequest): Authentication {
        return Authentication(
            password = value.password, phoneNumber = value.phoneNumber
        )
    }
}