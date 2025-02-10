package uz.mobilesoft.cleanarchitecture.data.models

import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam

class AuthRequest(
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = ""
)

fun RegistrationParam.mapToAuthRequest() = AuthRequest(
    email = email,
    password = password,
    phoneNumber = phoneNumber
)