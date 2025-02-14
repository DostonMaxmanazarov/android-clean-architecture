package uz.mobilesoft.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.databinding.FragmentForgotPasswordBinding
import uz.mobilesoft.data.repository.AuthRepositoryImpl
import uz.mobilesoft.data.storage.AuthStorageSharedPref
import uz.mobilesoft.data.storage.impl.AuthStorageSharedPrefImpl
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.ResetForgotPasswordUseCase
import uz.mobilesoft.domain.usecase.impl.ResetForgotPasswordUseCaseImpl
import uz.mobilesoft.presentation.utils.replaceFragment
import uz.mobilesoft.presentation.viewmodel.ForgotPassViewModel
import uz.mobilesoft.presentation.viewmodel.LoginViewModel
import uz.mobilesoft.presentation.viewmodel.factory.ForgotPassViewModelFactory
import uz.mobilesoft.presentation.viewmodel.factory.LoginViewModelFactory

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ForgotPassViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentForgotPasswordBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this, ForgotPassViewModelFactory(context = requireContext())
        )[ForgotPassViewModel::class.java]

        onViewClicked()

        observeData()
    }

    private fun onViewClicked() = binding.apply {
        btnForgotPass.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()
            viewModel.forgotPassword(phoneNumber)
        }
    }

    private fun observeData() {
        viewModel.resultLiveData.observe(viewLifecycleOwner) { success ->
            if (success) replaceFragment(
                container = R.id.container,
                fragment = OtpVerificationFragment(),
                addToBackStack = true,
                args = bundleOf(SCREEN_TYPE to ForgotPasswordFragment::class.java.simpleName)
            )
            else showToast(R.string.request_failed)
        }

        viewModel.phoneNumberErrorLiveData.observe(viewLifecycleOwner) {
            showToast(R.string.please_full_phone_number)
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