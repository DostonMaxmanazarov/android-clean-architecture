package uz.mobilesoft.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.usecase.VerifyOtpUseCase

class OtpViewModel(
    val otpUseCase: VerifyOtpUseCase
) : ViewModel() {

    private var _resultLiveData = MutableLiveData<Boolean>()
    val resultLiveData: LiveData<Boolean> get() = _resultLiveData

    private var _otpErrorLiveData = MutableLiveData<Unit>()
    val otpErrorLiveData: LiveData<Unit> get() = _otpErrorLiveData

    fun verifyOtp(code: String) {
        val result = otpUseCase(code)
        when (result) {
            AuthResult.Success -> _resultLiveData.value = true
            AuthResult.Error -> _resultLiveData.value = false
            AuthResult.OtpError-> _otpErrorLiveData.value = Unit
            else -> {}
        }
    }
}