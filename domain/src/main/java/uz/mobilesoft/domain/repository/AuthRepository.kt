package uz.mobilesoft.domain.repository

import uz.mobilesoft.domain.models.params.RegistrationParam
import uz.mobilesoft.domain.models.Authentication

interface AuthRepository {
    fun saveAuthentication(saveParam: RegistrationParam): Boolean
    fun getAuthentication(): Authentication
}