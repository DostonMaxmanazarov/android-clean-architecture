package uz.mobilesoft.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.usecase.ResetForgotPasswordUseCase

class ForgotPassViewModel(
    val forgotPassUseCase: ResetForgotPasswordUseCase
) : ViewModel() {

    private var _resultLiveData = MutableLiveData<Boolean>()
    val resultLiveData: LiveData<Boolean> get() = _resultLiveData

    private var _phoneNumberErrorLiveData = MutableLiveData<Unit>()
    val phoneNumberErrorLiveData: LiveData<Unit> get() = _phoneNumberErrorLiveData

    fun forgotPassword(phoneNumber: String) {
        val result = forgotPassUseCase(phoneNumber)
        when (result) {
            AuthResult.Success -> _resultLiveData.value = true
            AuthResult.Error -> _resultLiveData.value = false
            AuthResult.PhoneNumberError-> _phoneNumberErrorLiveData.value = Unit
            else -> {}
        }
    }
}