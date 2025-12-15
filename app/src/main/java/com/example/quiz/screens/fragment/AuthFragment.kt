package com.example.quiz.screens.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.quiz.R
import com.example.quiz.databinding.FragmentAuthBinding
import com.example.quiz.screens.common.BaseFragment
import com.example.quiz.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment: BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: AuthViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextEmail.doAfterTextChanged {
            viewModel.onEmailChanged(it.toString())
        }

        binding.editTextPassword.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }

        binding.buttonLoginOrRegister.setOnClickListener {
            var isValid = true
            binding.emailLayout.error = null
            binding.passwordLayout.error = null

            if (binding.editTextEmail.text.toString().isEmpty()) {
                binding.emailLayout.error = "Введите email"
                isValid = false
            }

            if (binding.editTextPassword.text.toString().isEmpty()) {
                binding.passwordLayout.error = "Введите пароль"
                isValid = false
            }

            if (!isValid) return@setOnClickListener
            if (binding.buttonLoginOrRegister.text == getString(R.string.auth)) {
                viewModel.tryLogin()
            } else {
                viewModel.register()
            }
        }

        binding.buttonGoToRegistrationOrAuth.setOnClickListener {
            if (binding.buttonGoToRegistrationOrAuth.text == getString(R.string.go_to_registration)) {
                binding.buttonLoginOrRegister.text = getString(R.string.registration)
                binding.textViewLoginOrRegistration.text = getString(R.string.registration_text)
                binding.buttonGoToRegistrationOrAuth.text = getString(R.string.go_to_auth)
            } else {
                binding.buttonLoginOrRegister.text = getString(R.string.auth)
                binding.textViewLoginOrRegistration.text = getString(R.string.auth_text)
                binding.buttonGoToRegistrationOrAuth.text = getString(R.string.go_to_registration)
            }
        }

        lifecycleScope.launch {
            viewModel.authResult.collectLatest { result ->
                result?.onSuccess { user ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main, TopicsFragment.newInstance())
                        .commit()
                }
                result?.onFailure { error ->
                    Toast.makeText(requireContext(), error.message ?: "Ошибка", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}