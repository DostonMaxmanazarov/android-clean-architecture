package uz.mobilesoft.cleanarchitecture.domain.models

sealed class AuthResult {
    object Error : AuthResult()
    object Success : AuthResult()
    object PhoneNumberError : AuthResult()
    object PasswordError : AuthResult()
    object PasswordConfirmError : AuthResult()
}