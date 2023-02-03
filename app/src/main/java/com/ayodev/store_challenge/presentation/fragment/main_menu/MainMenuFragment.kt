package com.ayodev.store_challenge.presentation.fragment.main_menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ayodev.store_challenge.R
import com.ayodev.store_challenge.databinding.FragmentMainMenuBinding
import com.ayodev.store_challenge.presentation.activity.maps.MapsActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainMenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mmBtnVisit.setOnClickListener {
            startActivity(Intent(requireActivity(), MapsActivity::class.java))
        }

        binding.mmBtnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.menu_logout))
                .setMessage(resources.getString(R.string.confirm_logout))
                .setNegativeButton(resources.getString(R.string.btn_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(resources.getString(R.string.menu_logout)) { _, _ ->
                    viewModel.logout()
                    findNavController().navigate(R.id.action_mainMenuFragment_to_loginFragment)
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "MainMenuFragment"
    }


}