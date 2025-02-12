package uz.mobilesoft.domain.models

data class NewPasswordParam(
    val password: String,
    val passwordConfirm: String
)