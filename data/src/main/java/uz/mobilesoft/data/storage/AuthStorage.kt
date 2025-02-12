package uz.mobilesoft.data.storage

import uz.mobilesoft.data.storage.models.AuthenticationRequest

interface AuthStorage {
    fun saveAuthentication(user: AuthenticationRequest): Boolean
    fun getAuthentication(): AuthenticationRequest
}