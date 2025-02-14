package uz.mobilesoft.presentation.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.mobilesoft.data.repository.AuthRepositoryImpl
import uz.mobilesoft.data.storage.AuthStorageSharedPref
import uz.mobilesoft.data.storage.impl.AuthStorageSharedPrefImpl
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.ExecuteLoginUseCase
import uz.mobilesoft.domain.usecase.SaveNewPasswordUseCase
import uz.mobilesoft.domain.usecase.impl.ExecuteLoginUseCaseImpl
import uz.mobilesoft.domain.usecase.impl.SaveNewPasswordUseCaseImpl
import uz.mobilesoft.presentation.viewmodel.NewPassViewModel

class NewPassViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = context)
    }
    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(authStorage = authStorage)
    }
    private val newPassUseCase: SaveNewPasswordUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveNewPasswordUseCaseImpl(authRepository = authRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewPassViewModel(newPassUseCase) as T
    }
}