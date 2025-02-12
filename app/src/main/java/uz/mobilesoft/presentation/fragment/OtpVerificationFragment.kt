package uz.mobilesoft.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.databinding.FragmentOtpVerificationBinding
import uz.mobilesoft.data.repository.AuthRepositoryImpl
import uz.mobilesoft.data.storage.AuthStorageSharedPref
import uz.mobilesoft.data.storage.impl.AuthStorageSharedPrefImpl
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.VerifyOtpUseCase
import uz.mobilesoft.domain.usecase.impl.VerifyOtpUseCaseImpl
import uz.mobilesoft.presentation.utils.extensions.replaceFragment

class OtpVerificationFragment : Fragment(R.layout.fragment_otp_verification) {
    private var _binding: FragmentOtpVerificationBinding? = null
    private val binding get() = _binding!!

    private var screenType: String? = null

    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = requireContext())
    }

    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(authStorage = authStorage)
    }

    private val otpUseCase: VerifyOtpUseCase by lazy(LazyThreadSafetyMode.NONE) {
        VerifyOtpUseCaseImpl(authRepository = authRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentOtpVerificationBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        screenType = requireArguments().getString(SCREEN_TYPE)
        onViewClicked()
    }

    private fun onViewClicked() = binding.apply {
        btnVerify.setOnClickListener {
            val code = etOtp.text.toString()
            val result = otpUseCase.invoke(code)
            handlingResult(result)
        }
    }

    private fun handlingResult(result: AuthResult) {
        when (result) {
            AuthResult.Success -> {
                when (screenType) {
                    RegistrationFragment::class.java.simpleName -> replaceFragment(
                        container = R.id.container,
                        fragment = MainFragment(),
                        addToBackStack = true
                    )

                    ForgotPasswordFragment::class.java.simpleName -> replaceFragment(
                        container = R.id.container,
                        fragment = NewPasswordFragment(),
                        addToBackStack = true
                    )

                    null -> {}
                }
            }

            AuthResult.Error -> showToast(R.string.request_failed)

            AuthResult.OtpError -> showToast(R.string.invalid_otp_code)

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