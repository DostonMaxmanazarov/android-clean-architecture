package uz.mobilesoft.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.databinding.FragmentRegistrationBinding
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.presentation.utils.replaceFragment
import uz.mobilesoft.domain.models.RegistrationParam
import uz.mobilesoft.presentation.viewmodel.RegistrationViewModel
import uz.mobilesoft.presentation.viewmodel.factory.RegistrationViewModelFactory

const val SCREEN_TYPE = "screen_type"

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegistrationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegistrationBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this, RegistrationViewModelFactory(context = requireContext())
        )[RegistrationViewModel::class.java]

        onViewClicked()

        observeData()
    }

    private fun onViewClicked() = binding.apply {
        btnSaveData.setOnClickListener {
            val password = etPassword.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            val registrationParams = RegistrationParam(
                password = password,
                phoneNumber = phoneNumber,
                confirmPassword = confirmPassword
            )

            viewModel.registration(registrationParams)
        }

        tvLogin.setOnClickListener {
            replaceFragment(
                container = R.id.container, fragment = LoginFragment(), addToBackStack = true
            )
        }
    }

    private fun observeData() {
        viewModel.resultLiveData.observe(viewLifecycleOwner) { success ->
            if (success) replaceFragment(
                container = R.id.container,
                fragment = OtpVerificationFragment(),
                addToBackStack = true,
                args = bundleOf(SCREEN_TYPE to RegistrationFragment::class.java.simpleName)
            )
            else showToast(R.string.request_failed)
        }

        viewModel.phoneNumberErrorLiveData.observe(viewLifecycleOwner){
            showToast(R.string.please_full_phone_number)
        }

        viewModel.passErrorLiveData.observe(viewLifecycleOwner){
            showToast(R.string.password_must_be_full)
        }

        viewModel.passConfirmErrorLiveData.observe(viewLifecycleOwner){
            showToast(R.string.password_and_password_confirm_equal)
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