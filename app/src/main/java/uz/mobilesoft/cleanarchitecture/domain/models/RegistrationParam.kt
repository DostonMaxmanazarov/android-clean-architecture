package uz.mobilesoft.cleanarchitecture.domain.models

data class RegistrationParam(
    val password: String = "",
    val phoneNumber: String = "",
    val confirmPassword: String = ""
)