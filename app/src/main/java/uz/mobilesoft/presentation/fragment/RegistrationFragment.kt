package uz.mobilesoft.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.databinding.FragmentRegistrationBinding
import uz.mobilesoft.presentation.utils.replaceFragment
import uz.mobilesoft.data.repository.AuthRepositoryImpl
import uz.mobilesoft.data.storage.AuthStorageSharedPref
import uz.mobilesoft.data.storage.impl.AuthStorageSharedPrefImpl
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.models.RegistrationParam
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.ExecuteRegistrationUseCase
import uz.mobilesoft.domain.usecase.impl.ExecuteRegistrationUseCaseImpl

const val SCREEN_TYPE = "screen_type"

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = requireContext())
    }

    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(authStorage = authStorage)
    }

    private val registrationUseCase: ExecuteRegistrationUseCase by lazy(LazyThreadSafetyMode.NONE) {
        ExecuteRegistrationUseCaseImpl(authRepository = authRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegistrationBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initClick()
    }

    private fun initClick() = binding.apply {
        btnSaveData.setOnClickListener {
            val password = etPassword.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            val registrationParams = RegistrationParam(
                password = password, phoneNumber = phoneNumber, confirmPassword = confirmPassword
            )
            val result = registrationUseCase.invoke(param = registrationParams)
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
            AuthResult.Success -> replaceFragment(
                container = R.id.container,
                fragment = OtpVerificationFragment(),
                addToBackStack = true,
                args = bundleOf(SCREEN_TYPE to RegistrationFragment::class.java.simpleName)
            )

            AuthResult.Error -> showToast(R.string.request_failed)

            AuthResult.PasswordError -> showToast(R.string.password_must_be_full)

            AuthResult.PhoneNumberError -> showToast(R.string.please_full_phone_number)

            AuthResult.PasswordConfirmError -> showToast(R.string.password_and_password_confirm_equal)

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