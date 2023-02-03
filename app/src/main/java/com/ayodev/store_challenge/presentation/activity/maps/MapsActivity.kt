package com.ayodev.store_challenge.presentation.activity.maps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayodev.store_challenge.R
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.databinding.ActivityMapsBinding
import com.ayodev.store_challenge.presentation.activity.visit.VisitActivity
import com.ayodev.store_challenge.util.LocationPermission
import com.ayodev.store_challenge.util.distanceInKm
import com.ayodev.store_challenge.util.toastLong
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapsBinding

    private val viewModel: MapsViewModel by viewModels()

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView

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

        setUpToolbar()
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

    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.mToolbar)
        supportActionBar?.apply {
            title = resources.getString(R.string.menu_store_list)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun showStoreList() {
        viewModel.getAllStore().observe(this) {
            when(it) {
                is Resource.Success -> {
                    if(it.data != null) {
                        setUpStoreList(it.data)
                        Log.d(TAG, it.data.toString())
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
        Log.d(TAG, location.toString())

        storeAdapter.onItemClick = {
            val intent = Intent(this, VisitActivity::class.java)
            intent.putExtra("store", it)
            startActivity(intent)
        }

        storeAdapter.setItems(
            data.map {
                val distanceToUser =distanceInKm(location!!.latitude, location.longitude, it.latitude, it.longitude) * 1000
                Log.d(TAG, distanceToUser.toString())
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

    override fun onMapReady(gMap: GoogleMap) {
        mMap = gMap
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    companion object {
        const val TAG = "MapsActivity"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}