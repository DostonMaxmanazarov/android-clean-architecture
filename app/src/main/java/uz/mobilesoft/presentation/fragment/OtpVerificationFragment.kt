package uz.mobilesoft.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.databinding.FragmentOtpVerificationBinding
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.presentation.utils.replaceFragment
import uz.mobilesoft.presentation.viewmodel.LoginViewModel
import uz.mobilesoft.presentation.viewmodel.OtpViewModel
import uz.mobilesoft.presentation.viewmodel.factory.LoginViewModelFactory
import uz.mobilesoft.presentation.viewmodel.factory.OtpViewModelFactory

class OtpVerificationFragment : Fragment(R.layout.fragment_otp_verification) {
    private var _binding: FragmentOtpVerificationBinding? = null
    private val binding get() = _binding!!

    private var screenType: String? = null

    private lateinit var viewModel: OtpViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentOtpVerificationBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        screenType = requireArguments().getString(SCREEN_TYPE)

        viewModel = ViewModelProvider(
            this, OtpViewModelFactory(context = requireContext())
        )[OtpViewModel::class.java]

        onViewClicked()

        observeData()
    }

    private fun onViewClicked() = binding.apply {
        btnVerify.setOnClickListener {
            val code = etOtp.text.toString()
            viewModel.verifyOtp(code)
        }
    }

    private fun observeData() {
        viewModel.resultLiveData.observe(viewLifecycleOwner) { success ->
            if (success) when (screenType) {
                RegistrationFragment::class.java.simpleName -> replaceFragment(
                    container = R.id.container, fragment = MainFragment(), addToBackStack = true
                )

                ForgotPasswordFragment::class.java.simpleName -> replaceFragment(
                    container = R.id.container,
                    fragment = NewPasswordFragment(),
                    addToBackStack = true
                )

                null -> {}
            }
            else showToast(R.string.request_failed)
        }

        viewModel.otpErrorLiveData.observe(viewLifecycleOwner) {
            showToast(R.string.invalid_otp_code)
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