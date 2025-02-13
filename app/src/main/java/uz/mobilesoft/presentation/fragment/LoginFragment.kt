package uz.mobilesoft.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
import uz.mobilesoft.presentation.viewmodel.LoginViewModel
import uz.mobilesoft.presentation.viewmodel.factory.LoginViewModelFactory

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this, LoginViewModelFactory(context = requireContext())
        )[LoginViewModel::class.java]

        onViewClicked()

        observeData()
    }

    private fun onViewClicked() = binding.apply {
        btnLogin.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()
            val password = etPassword.text.toString()

            val loginParam = LoginParam(
                phoneNumber = phoneNumber, password = password
            )

            viewModel.login(loginParam)
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

    private fun observeData() {
        viewModel.resultLiveData.observe(viewLifecycleOwner) { success ->
            if (success) replaceFragment(
                container = R.id.container,
                fragment = MainFragment(),
                addToBackStack = true
            )

            else showToast(R.string.request_failed)
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