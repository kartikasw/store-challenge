package com.ayodev.store_challenge.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ayodev.store_challenge.presentation.login.LoginActivity
import com.ayodev.store_challenge.presentation.main_menu.MainMenuActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        if(viewModel.getLoginStatus()) {
            startActivity(Intent(this, MainMenuActivity::class.java))
            finishAffinity()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
    }
}