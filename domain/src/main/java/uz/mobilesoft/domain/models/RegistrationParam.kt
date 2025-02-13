package uz.mobilesoft.domain.models

data class RegistrationParam(
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val confirmPassword: String = ""
)
