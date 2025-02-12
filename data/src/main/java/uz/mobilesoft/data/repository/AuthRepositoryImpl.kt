package uz.mobilesoft.data.repository

import uz.mobilesoft.data.mapper.SingleMapper
import uz.mobilesoft.data.storage.AuthStorage
import uz.mobilesoft.data.storage.models.AuthenticationRequest
import uz.mobilesoft.domain.models.*
import uz.mobilesoft.domain.models.params.RegistrationParam
import uz.mobilesoft.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authStorage: AuthStorage,
    private val saveAuthParamMapToStorage: SingleMapper<RegistrationParam, AuthenticationRequest>,
    private val authRequestMapToDomain: SingleMapper<AuthenticationRequest, Authentication>
) : AuthRepository {

    override fun saveAuthentication(saveParam: RegistrationParam): Boolean {
        val authentication = saveAuthParamMapToStorage(value = saveParam)
        return authStorage.saveAuthentication(authentication)
    }

    override fun getAuthentication(): Authentication {
        val authentication = authStorage.getAuthentication()
        return authRequestMapToDomain(value = authentication)
    }
}