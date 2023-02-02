package com.ayodev.store_challenge.presentation.fragment.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ayodev.store_challenge.R
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.databinding.FragmentLoginBinding
import com.ayodev.store_challenge.util.custom_view.AppLoadingDialog
import com.ayodev.store_challenge.util.toastLong
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var loading: AppLoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading = AppLoadingDialog(requireContext())

        binding.lBtnLogin.setOnClickListener{
            it.login()
        }
    }

    private fun View.login() {
        val username = binding.lEtUsername
        val usernameData = username.text.toString()
        val password = binding.lEtPassword
        val passwordData = password.text.toString()

        if(username.error == null && password.error == null
            && usernameData.isNotEmpty() && passwordData.isNotEmpty()) {

            lifecycleScope.launch {
                viewModel.login(usernameData, passwordData).observe(viewLifecycleOwner) {
                    when(it) {
                        is Resource.Success -> {
                            loading.dismiss()
                            requireContext().toastLong("Login berhasil")
                            Log.d(TAG, "login berhasil")
                            this@login.findNavController().navigate(R.id.action_loginFragment_to_mainMenuFragment)
                        }
                        is Resource.Loading -> {
                            loading.show()
                        }
                        is Resource.Error -> {
                            loading.dismiss()
                            Log.e(TAG, it.message.toString())
                            requireContext().toastLong(it.message.toString())
                            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_mainMenuFragment)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "LoginFragment"
    }
}