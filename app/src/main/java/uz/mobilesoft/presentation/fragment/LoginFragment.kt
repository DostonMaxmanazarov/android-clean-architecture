package uz.mobilesoft.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.databinding.FragmentLoginBinding
import uz.mobilesoft.data.repository.AuthRepositoryImpl
import uz.mobilesoft.data.storage.AuthStorageSharedPref
import uz.mobilesoft.data.storage.impl.AuthStorageSharedPrefImpl
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.models.LoginParam
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.ExecuteLoginUseCase
import uz.mobilesoft.domain.usecase.impl.ExecuteLoginUseCaseImpl
import uz.mobilesoft.presentation.utils.replaceFragment

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = requireContext())
    }
    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(authStorage = authStorage)
    }
    private val loginUseCase: ExecuteLoginUseCase by lazy(LazyThreadSafetyMode.NONE) {
        ExecuteLoginUseCaseImpl(authRepository = authRepository)
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
                phoneNumber = phoneNumber, password = password
            )
            val result = loginUseCase.invoke(param = loginParam)
            handlingResult(result)
        }

        tvRegistration.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        tvForgotPassword.setOnClickListener {
            replaceFragment(
                container = R.id.container,
                fragment = ForgotPasswordFragment(),
                addToBackStack = true
            )
        }
    }

    private fun handlingResult(result: AuthResult) {
        when (result) {
            AuthResult.Success -> replaceFragment(
                container = R.id.container,
                fragment = MainFragment(),
                addToBackStack = true
            )

            AuthResult.Error -> showToast(R.string.request_failed)

            AuthResult.PasswordError -> showToast(R.string.password_must_be_full)

            AuthResult.PhoneNumberError -> showToast(R.string.please_full_phone_number)

            else -> showToast(R.string.failed)
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