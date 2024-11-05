package uz.apexsoft.presentation.di

import dagger.Component
import uz.apexsoft.presentation.fragment.LoginFragment
import uz.apexsoft.presentation.fragment.RegistrationFragment

@Component(modules = [ApplicationModule::class, DomainModule::class, DataModule::class])
interface ApplicationComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(registrationFragment: RegistrationFragment)
}