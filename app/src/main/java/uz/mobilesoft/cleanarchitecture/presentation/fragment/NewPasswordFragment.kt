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
import uz.mobilesoft.cleanarchitecture.databinding.FragmentForgotPasswordBinding
import uz.mobilesoft.cleanarchitecture.databinding.FragmentNewPasswordBinding
import uz.mobilesoft.cleanarchitecture.domain.models.AuthResult
import uz.mobilesoft.cleanarchitecture.domain.models.NewPasswordParam
import uz.mobilesoft.cleanarchitecture.domain.repository.AuthRepository
import uz.mobilesoft.cleanarchitecture.domain.usecase.ExecuteRegistrationUseCase
import uz.mobilesoft.cleanarchitecture.domain.usecase.SaveNewPasswordUseCase
import uz.mobilesoft.cleanarchitecture.domain.usecase.impl.ExecuteRegistrationUseCaseImpl
import uz.mobilesoft.cleanarchitecture.domain.usecase.impl.SaveNewPasswordUseCaseImpl
import uz.mobilesoft.cleanarchitecture.presentation.utils.extensions.replaceFragment

class NewPasswordFragment : Fragment(R.layout.fragment_new_password) {
    private var _binding: FragmentNewPasswordBinding? = null
    private val binding get() = _binding!!

    private val authStorage: AuthStorageSharedPref by lazy(LazyThreadSafetyMode.NONE) {
        AuthStorageSharedPrefImpl(context = requireContext())
    }

    private val authRepository: AuthRepository by lazy(LazyThreadSafetyMode.NONE) {
        AuthRepositoryImpl(authStorage = authStorage)
    }

    private val newPasswordUseCase: SaveNewPasswordUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveNewPasswordUseCaseImpl(authRepository = authRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentNewPasswordBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        onViewClicked()
    }

    private fun onViewClicked() = binding.apply {
        btnDone.setOnClickListener {
            val password = etPassword.text.toString()
            val passwordConfirm = etConfirmPassword.text.toString()
            val param = NewPasswordParam(
                password = password, passwordConfirm = passwordConfirm
            )
            val result = newPasswordUseCase.invoke(param)
            handlingResult(result)
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

            AuthResult.PasswordConfirmError -> showToast(R.string.password_and_password_confirm_equal)

            AuthResult.PasswordError -> showToast(R.string.password_must_be_full)

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