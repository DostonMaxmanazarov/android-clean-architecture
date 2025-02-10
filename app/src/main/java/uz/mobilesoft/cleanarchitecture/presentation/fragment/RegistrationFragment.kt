package uz.mobilesoft.cleanarchitecture.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.presentation.utils.extensions.replaceFragment
import uz.mobilesoft.cleanarchitecture.data.repository.AuthRepositoryImpl
import uz.mobilesoft.cleanarchitecture.data.storage.AuthStorageSharedPref
import uz.mobilesoft.cleanarchitecture.data.storage.impl.AuthStorageSharedPrefImpl
import uz.mobilesoft.cleanarchitecture.databinding.FragmentRegistrationBinding
import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.models.RegistrationParam
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.PerformRegistrationUseCase
import uz.mobilesoft.cleanarchitecture.domain.usecase.impl.PerformRegistrationUseCaseImpl

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = requireContext())
    }

    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(authStorage = authStorage)
    }

    private val saveAuthUseCase: PerformRegistrationUseCase by lazy(LazyThreadSafetyMode.NONE) {
        PerformRegistrationUseCaseImpl(authRepository = authRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegistrationBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initClick()
    }

    private fun initClick() = binding.apply {
        btnSaveData.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            val registrationParams = RegistrationParam(
                email = email,
                password = password,
                phoneNumber = phoneNumber,
                confirmPassword = confirmPassword
            )
            val result = saveAuthUseCase.invoke(param = registrationParams)
            handlingResult(result)
        }

        tvLogin.setOnClickListener {
            replaceFragment(
                container = R.id.container, fragment = LoginFragment(), addToBackStack = true
            )
        }
    }

    private fun handlingResult(result: AuthResult) {
        when (result) {
            AuthResult.Success -> showToast(R.string.success)
            AuthResult.Error -> showToast(R.string.failed)
            AuthResult.PasswordConfirmError -> {}
            AuthResult.PasswordError -> {}
            AuthResult.PhoneNumberError -> {}
            AuthResult.EmailError -> {}

        }
    }

    private fun showToast(@StringRes msgId: Int) {
        Toast.makeText(requireContext(), msgId, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}