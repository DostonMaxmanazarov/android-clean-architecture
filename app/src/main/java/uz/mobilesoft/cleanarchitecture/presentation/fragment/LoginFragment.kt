package uz.mobilesoft.cleanarchitecture.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.data.repository.AuthRepositoryImpl
import uz.mobilesoft.cleanarchitecture.data.storage.AuthStorageSharedPref
import uz.mobilesoft.cleanarchitecture.data.storage.impl.AuthStorageSharedPrefImpl
import uz.mobilesoft.cleanarchitecture.databinding.FragmentLoginBinding
import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.models.LoginParam
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.PerformLoginUseCase
import uz.mobilesoft.cleanarchitecture.domain.usecase.impl.PerformLoginUseCaseImpl

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = requireContext())
    }
    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(authStorage = authStorage)
    }
    private val loginUseCase: PerformLoginUseCase by lazy(LazyThreadSafetyMode.NONE) {
        PerformLoginUseCaseImpl(
            authRepository = authRepository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initClickView()
    }

    private fun initClickView() = binding.apply {
        btnLogin.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()
            val password = etPassword.text.toString()
            val loginParam = LoginParam(
                phoneNumber = phoneNumber,
                password = password
            )
            val result = loginUseCase.invoke(param = loginParam)
            handlingResult(result)
        }
    }

    private fun handlingResult(result: AuthResult) {
        when (result) {
            AuthResult.Success -> showToast(R.string.success)
            AuthResult.Error -> showToast(R.string.failed)
            AuthResult.PasswordError -> {}
            AuthResult.PhoneNumberError -> {}
            else -> {}
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
