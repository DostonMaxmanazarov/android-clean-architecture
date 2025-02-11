package uz.mobilesoft.cleanarchitecture.data.models

import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam

class AuthRequest(
    val password: String = "",
    val phoneNumber: String = ""
)

fun RegistrationParam.mapToAuthRequest() = AuthRequest(
    password = password,
    phoneNumber = phoneNumber
)