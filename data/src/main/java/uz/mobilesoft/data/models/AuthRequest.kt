package uz.mobilesoft.data.models

import uz.mobilesoft.domain.models.RegistrationParam

class AuthRequest(
    val password: String = "",
    val phoneNumber: String = ""
)

fun RegistrationParam.mapToAuthRequest() = AuthRequest(
    password = password,
    phoneNumber = phoneNumber
)