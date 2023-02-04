package com.ayodev.store_challenge.presentation.detail_store

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayodev.store_challenge.R
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.databinding.ActivityDetailStoreBinding
import com.ayodev.store_challenge.presentation.maps.MapsActivity
import com.ayodev.store_challenge.util.storeInfoList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailStoreBinding

    private val viewModel: DetailStoreViewModel by viewModels()

    private lateinit var storeInfoAdapter: InfoAdapter

    private lateinit var store: Store

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storeId = intent.getIntExtra("store_id", 0)
        storeInfoAdapter = InfoAdapter()

        if(storeId == 0) {
            val intent = Intent(this, MapsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                store = viewModel.getStoreById(storeId)
                setUpToolbar()
                setUpStoreInfo()
            }
        }

        binding.dsMarquee.isSelected = true
        setUpStoreInfoList()

        binding.dsBtnEnd.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.store_menu, menu)
        return true
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.dsToolbar)
        supportActionBar?.subtitle = store.store_name
    }

    private fun setUpStoreInfo() {
        binding.apply {
            dsIvStore.setImageBitmap(store.image)
            dTvId.text = store.store_id.toString()
            dsTvName.text = store.store_name
        }
    }

    private fun setUpStoreInfoList() {
        storeInfoAdapter.setItems(storeInfoList)
        with(binding.dsRvInfo) {
            layoutManager = LinearLayoutManager(this@DetailStoreActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = storeInfoAdapter
        }
    }

}