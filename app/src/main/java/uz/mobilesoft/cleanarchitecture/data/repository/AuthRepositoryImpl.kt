package uz.mobilesoft.cleanarchitecture.data.repository

import uz.mobilesoft.cleanarchitecture.data.mapper.SingleMapper
import uz.mobilesoft.cleanarchitecture.data.storage.AuthStorageSharedPref
import uz.mobilesoft.cleanarchitecture.data.storage.models.AuthenticationRequest
import uz.mobilesoft.cleanarchitecture.domain.models.Authentication
import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authStorage: AuthStorageSharedPref,
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