package uz.mobilesoft.cleanarchitecture.domain.models

data class NewPasswordParam(
    val password: String,
    val passwordConfirm: String
)