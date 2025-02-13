package uz.mobilesoft.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.models.LoginParam
import uz.mobilesoft.domain.usecase.ExecuteLoginUseCase

class LoginViewModel(
    val loginUseCase: ExecuteLoginUseCase
) : ViewModel() {

    private var _resultLiveData = MutableLiveData<Boolean>()
    val resultLiveData: LiveData<Boolean> get() = _resultLiveData

    fun login(loginParam: LoginParam) {
        val result = loginUseCase(param = loginParam)
        when (result) {
            AuthResult.Success -> _resultLiveData.value = true
            else -> {_resultLiveData.value = false}
        }
    }
}