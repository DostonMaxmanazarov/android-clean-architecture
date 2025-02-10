package uz.mobilesoft.cleanarchitecture.data.mapper.impl

import uz.mobilesoft.cleanarchitecture.data.mapper.SingleMapper
import uz.mobilesoft.cleanarchitecture.data.models.AuthRequest
import uz.mobilesoft.cleanarchitecture.domain.models.Authentication

class AuthenticationRequestMapToDomain :
    SingleMapper<AuthRequest, Authentication> {
    override fun invoke(value: AuthRequest): Authentication {
        return Authentication(
            password = value.password, phoneNumber = value.phoneNumber
        )
    }
}