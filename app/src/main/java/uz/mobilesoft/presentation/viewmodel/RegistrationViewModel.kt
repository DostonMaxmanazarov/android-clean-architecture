package uz.mobilesoft.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.models.RegistrationParam
import uz.mobilesoft.domain.usecase.ExecuteRegistrationUseCase

class RegistrationViewModel(
    private val registrationUseCase: ExecuteRegistrationUseCase
) : ViewModel() {

    private var _resultLiveData = MutableLiveData<Boolean>()
    val resultLiveData: LiveData<Boolean> get() = _resultLiveData

    fun registration(param: RegistrationParam) {
        val result = registrationUseCase(param = param)

        when (result) {
            AuthResult.Success -> _resultLiveData.value = true
            else -> {_resultLiveData.value = false}
        }
    }
}