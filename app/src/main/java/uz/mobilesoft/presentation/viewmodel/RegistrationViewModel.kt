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

    private var _phoneNumberErrorLiveData = MutableLiveData<Unit>()
    val phoneNumberErrorLiveData: LiveData<Unit> get() = _phoneNumberErrorLiveData

    private var _passErrorLiveData = MutableLiveData<Unit>()
    val passErrorLiveData: LiveData<Unit> get() = _passErrorLiveData

    private var _passConfirmErrorLiveData = MutableLiveData<Unit>()
    val passConfirmErrorLiveData: LiveData<Unit> get() = _passConfirmErrorLiveData

    fun registration(param: RegistrationParam) {
        val result = registrationUseCase(param = param)

        when (result) {
            AuthResult.Success -> _resultLiveData.value = true
            AuthResult.Error -> _resultLiveData.value = false
            AuthResult.PasswordError -> _passErrorLiveData.value = Unit
            AuthResult.PasswordConfirmError -> _passConfirmErrorLiveData.value = Unit
            AuthResult.PhoneNumberError -> _phoneNumberErrorLiveData.value = Unit
            else -> {_resultLiveData.value = false}
        }
    }
}