package uz.mobilesoft.data.mapper.impl

import uz.mobilesoft.data.mapper.SingleMapper
import uz.mobilesoft.data.storage.models.AuthenticationRequest
import uz.mobilesoft.domain.models.Authentication

class AuthenticationRequestMapToDomain :
    SingleMapper<AuthenticationRequest, Authentication> {
    override fun invoke(value: AuthenticationRequest): Authentication {
        return Authentication(
            password = value.password, phoneNumber = value.phoneNumber
        )
    }
}