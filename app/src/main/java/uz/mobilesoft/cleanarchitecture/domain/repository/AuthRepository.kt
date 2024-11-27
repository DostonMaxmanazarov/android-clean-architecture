package uz.mobilesoft.cleanarchitecture.domain.repository

import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam
import uz.mobilesoft.cleanarchitecture.domain.models.Authentication

interface AuthRepository {
    fun saveAuthentication(saveParam: RegistrationParam): Boolean
    fun getAuthentication(): Authentication
}