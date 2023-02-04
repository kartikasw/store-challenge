package com.ayodev.store_challenge.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.databinding.ActivityLoginBinding
import com.ayodev.store_challenge.presentation.main_menu.MainMenuActivity
import com.ayodev.store_challenge.util.custom_view.AppLoadingDialog
import com.ayodev.store_challenge.util.toastLong
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var loading: AppLoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loading = AppLoadingDialog(this)

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
                viewModel.login(usernameData, passwordData).observe(this@LoginActivity) {
                    when(it) {
                        is Resource.Success -> {
                            loading.dismiss()
                            toastLong("Login berhasil")
                            startActivity(Intent(this@LoginActivity, MainMenuActivity::class.java)).also {
                                finishAffinity()
                            }
                        }
                        is Resource.Loading -> {
                            loading.show()
                        }
                        is Resource.Error -> {
                            loading.dismiss()
                            Log.e(TAG, it.message.toString())
                            toastLong(it.message.toString())
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}