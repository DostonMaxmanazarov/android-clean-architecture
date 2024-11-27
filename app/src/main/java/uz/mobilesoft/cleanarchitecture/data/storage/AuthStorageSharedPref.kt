package uz.mobilesoft.cleanarchitecture.data.storage

import uz.mobilesoft.cleanarchitecture.data.storage.models.AuthenticationRequest

interface AuthStorageSharedPref {
    fun saveAuthentication(user: AuthenticationRequest): Boolean
    fun getAuthentication(): AuthenticationRequest
}