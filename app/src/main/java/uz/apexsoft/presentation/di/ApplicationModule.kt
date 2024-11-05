package uz.apexsoft.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import uz.apexsoft.presentation.vm.LoginViewModel
import uz.apexsoft.presentation.vm.RegistrationViewModel
import uz.apexsoft.presentation.vm.impl.LoginViewModelImpl
import uz.apexsoft.presentation.vm.impl.RegistrationViewModelImpl

var appModule = module {
    viewModel {
        RegistrationViewModelImpl(saveAuthUseCase = get())
    } bind RegistrationViewModel::class

    viewModel {
        LoginViewModelImpl(getAuthUseCase = get())
    } bind LoginViewModel::class
}