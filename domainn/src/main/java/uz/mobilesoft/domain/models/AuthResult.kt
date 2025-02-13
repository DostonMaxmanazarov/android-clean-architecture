package uz.mobilesoft.domain.models

sealed class AuthResult {
    object Error : AuthResult()
    object Success : AuthResult()
    object OtpError : AuthResult()
    object PhoneNumberError : AuthResult()
    object PasswordError : AuthResult()
    object PasswordConfirmError : AuthResult()
}