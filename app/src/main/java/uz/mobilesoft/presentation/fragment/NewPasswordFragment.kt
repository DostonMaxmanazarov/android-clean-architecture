package uz.mobilesoft.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.databinding.FragmentNewPasswordBinding
import uz.mobilesoft.data.repository.AuthRepositoryImpl
import uz.mobilesoft.data.storage.AuthStorageSharedPref
import uz.mobilesoft.data.storage.impl.AuthStorageSharedPrefImpl
import uz.mobilesoft.domain.models.AuthResult
import uz.mobilesoft.domain.models.NewPasswordParam
import uz.mobilesoft.domain.repository.AuthRepository
import uz.mobilesoft.domain.usecase.SaveNewPasswordUseCase
import uz.mobilesoft.domain.usecase.impl.SaveNewPasswordUseCaseImpl
import uz.mobilesoft.presentation.utils.replaceFragment
import uz.mobilesoft.presentation.viewmodel.NewPassViewModel
import uz.mobilesoft.presentation.viewmodel.factory.NewPassViewModelFactory

class NewPasswordFragment : Fragment(R.layout.fragment_new_password) {
    private var _binding: FragmentNewPasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewPassViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentNewPasswordBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this, NewPassViewModelFactory(context = requireContext())
        )[NewPassViewModel::class.java]

        onViewClicked()

        observeData()
    }

    private fun onViewClicked() = binding.apply {
        btnDone.setOnClickListener {
            val password = etPassword.text.toString()
            val passwordConfirm = etConfirmPassword.text.toString()
            val param = NewPasswordParam(
                password = password, passwordConfirm = passwordConfirm
            )
            viewModel.saveNewPassword(param)
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