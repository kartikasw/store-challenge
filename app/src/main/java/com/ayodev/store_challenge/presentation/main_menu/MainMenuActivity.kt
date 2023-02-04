package com.ayodev.store_challenge.presentation.main_menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ayodev.store_challenge.R
import com.ayodev.store_challenge.databinding.ActivityMainMenuBinding
import com.ayodev.store_challenge.presentation.login.LoginActivity
import com.ayodev.store_challenge.presentation.maps.MapsActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    private val viewModel: MainMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mmBtnVisit.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        binding.mmBtnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle(resources.getString(R.string.menu_logout))
                .setMessage(resources.getString(R.string.confirm_logout))
                .setNegativeButton(resources.getString(R.string.btn_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(resources.getString(R.string.menu_logout)) { _, _ ->
                    viewModel.logout()
                    startActivity(Intent(this, LoginActivity::class.java)).also {
                        finishAffinity()
                    }
                }
                .show()
        }
    }
}