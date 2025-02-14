package uz.mobilesoft.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.models.NewPasswordParam
import uz.mobilesoft.domain.usecase.ResetForgotPasswordUseCase
import uz.mobilesoft.domain.usecase.SaveNewPasswordUseCase

class NewPassViewModel(
    val newPassUseCase: SaveNewPasswordUseCase
) : ViewModel() {

    private var _resultLiveData = MutableLiveData<Boolean>()
    val resultLiveData: LiveData<Boolean> get() = _resultLiveData

    private var _passErrorLiveData = MutableLiveData<Unit>()
    val passErrorLiveData: LiveData<Unit> get() = _passErrorLiveData

    private var _passConfirmErrorLiveData = MutableLiveData<Unit>()
    val passConfirmErrorLiveData: LiveData<Unit> get() = _passConfirmErrorLiveData

    fun saveNewPassword(params: NewPasswordParam) {
        val result = newPassUseCase(params)
        when (result) {
            AuthResult.Success -> _resultLiveData.value = true
            AuthResult.Error -> _resultLiveData.value = false
            AuthResult.PasswordError-> _passErrorLiveData.value = Unit
            AuthResult.PasswordConfirmError-> _passErrorLiveData.value = Unit
            else -> {}
        }
    }
}