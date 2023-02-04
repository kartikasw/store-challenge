package com.ayodev.store_challenge.presentation.maps

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayodev.store_challenge.R
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.databinding.ActivityMapsBinding
import com.ayodev.store_challenge.presentation.visit.VisitActivity
import com.ayodev.store_challenge.util.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapsBinding

    private val viewModel: MapsViewModel by viewModels()

    private lateinit var storeAdapter: StoreAdapter
    private lateinit var rvStore: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locationPermission = LocationPermission(this) {
            it.accessFineLocationGranted.observe(this) { boolean ->
                if(boolean) {
                    viewModel.getCurrentLocation()
                }
            }
        }

        locationPermission.requestPermissions()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.m_maps) as SupportMapFragment

        setUpToolbar()
        setUpTitle()
        setUpSearch()
        storeAdapter = StoreAdapter()
        rvStore = binding.mRvStore

        viewModel.loading.observe(this) {
            if(it) {
                showLoading(true)
            } else {
                showStoreList()
                showLoading(false)
            }
        }

        mapFragment.getMapAsync(this)

    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.mToolbar)
        supportActionBar?.apply {
            title = resources.getString(R.string.menu_store_list)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpTitle() {
        val time = dateFormat.format(getCurrentTime())
        binding.mTitle.text = resources.getString(R.string.title_visit_list, time)
    }

    private fun showStoreList() {
        viewModel.getAllStore().observe(this) {
            when(it) {
                is Resource.Success -> {
                    if(it.data != null) {
                        viewModel.updateStores(it.data)
                        setUpStoreList(it.data)
                    }
                }
                is Resource.Error -> {
                    Log.e(TAG, it.message.toString())
                    toastLong(it.message!!)
                }
                else -> {}
            }
        }
    }

    private fun setUpStoreList(data: List<Store>) {
        val location = viewModel.location.value

        storeAdapter.onItemClick = {
            val intent = Intent(this, VisitActivity::class.java)
            intent.putExtra("store_id", it.id)
            startActivity(intent)
        }

        storeAdapter.setItems(
            data.map {
                val distanceToUser =distanceInKm(location!!.latitude, location.longitude, it.latitude, it.longitude)
                it.distance = distanceToUser
                it
            }
        )

        storeAdapter.setItems(data)

        with(rvStore) {
            layoutManager = LinearLayoutManager(this@MapsActivity)
            adapter = storeAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        binding.mLoading.root.isVisible = state
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(gMap: GoogleMap) {
        gMap.uiSettings.isZoomControlsEnabled = true
        gMap.uiSettings.isIndoorLevelPickerEnabled = true
        gMap.uiSettings.isCompassEnabled = true
        gMap.uiSettings.isMapToolbarEnabled = true

        viewModel.stores.observe(this) {
            if(it != null) {
                Log.d(TAG, "Stores in map: $it")
                for(i in it) {
                    Log.d(TAG, i.toString())
                    val location = LatLng(i.latitude, i.longitude)
                    gMap.addMarker(
                        MarkerOptions()
                            .position(location)
                            .title(i.store_name)
                    )

                }
            }
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(it[0].latitude, it[0].longitude), 10f
            ))

            gMap.isMyLocationEnabled = true

            gMap.uiSettings.isMyLocationButtonEnabled = true

            val useLocation = viewModel.location.value!!
            gMap.addCircle(
                CircleOptions()
                    .center(LatLng(useLocation.latitude, useLocation.longitude))
                    .radius(100.0)
                    .fillColor(0x22FF0000)
                    .strokeColor(Color.TRANSPARENT)
            )
        }
    }

    private fun setUpSearch() {
        binding.mSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchStore("%$newText%").observe(this@MapsActivity) {
                    when(it) {
                        is Resource.Success -> {
                            if(it.data != null) {
                                setUpStoreList(it.data)
                            }
                        }
                        is Resource.Error -> {
                            Log.e(TAG, it.message.toString())
                            toastLong(it.message!!)
                        }
                        else -> {}
                    }
                }
                return true
            }

        })
    }

    companion object {
        const val TAG = "MapsActivity"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}