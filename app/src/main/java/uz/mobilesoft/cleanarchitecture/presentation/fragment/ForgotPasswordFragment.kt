package uz.mobilesoft.cleanarchitecture.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.data.repository.AuthRepositoryImpl
import uz.mobilesoft.cleanarchitecture.data.storage.AuthStorageSharedPref
import uz.mobilesoft.cleanarchitecture.data.storage.impl.AuthStorageSharedPrefImpl
import uz.mobilesoft.cleanarchitecture.databinding.FragmentForgotPasswordBinding
import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.ResetForgotPasswordUseCase
import uz.mobilesoft.cleanarchitecture.domain.usecase.impl.ResetForgotPasswordUseCaseImpl
import uz.mobilesoft.cleanarchitecture.presentation.utils.extensions.replaceFragment

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = requireContext())
    }

    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(authStorage = authStorage)
    }

    private val forgotPasswordUseCase: ResetForgotPasswordUseCase by lazy(LazyThreadSafetyMode.NONE) {
        ResetForgotPasswordUseCaseImpl(authRepository = authRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentForgotPasswordBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        onViewClicked()
    }

    private fun onViewClicked() = binding.apply {
        btnForgotPass.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()
            val result = forgotPasswordUseCase.invoke(phoneNumber)
            handlingResult(result)
        }
    }

    private fun handlingResult(result: AuthResult) {
        when (result) {
            AuthResult.Success -> replaceFragment(
                container = R.id.container,
                fragment = OtpVerificationFragment(),
                addToBackStack = true,
                args = bundleOf(SCREEN_TYPE to ForgotPasswordFragment::class.java.simpleName)
            )

            AuthResult.Error -> showToast(R.string.request_failed)

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