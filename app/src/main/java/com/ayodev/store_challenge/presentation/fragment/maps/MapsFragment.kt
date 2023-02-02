package com.ayodev.store_challenge.presentation.fragment.maps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayodev.store_challenge.R
import com.ayodev.store_challenge.core.domain.Resource
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.util.toastLong
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView

    private val viewModel: MapsViewModel by viewModels()

    private lateinit var storeAdapter: StoreAdapter
    private lateinit var rvStore: RecyclerView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)
//        mapView = view.findViewById(R.id.m_maps) as MapView
//        mapView.onCreate(savedInstanceState)
//        mapView.getMapAsync(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storeAdapter = StoreAdapter()
        rvStore = view.findViewById(R.id.m_rv_store)
        toolbar = view.findViewById(R.id.m_toolbar)
        toolbar.setupWithNavController(findNavController())

        showStoreList()

    }

    private fun showStoreList() {
        viewModel.getAllStore().observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    if(it.data != null) {
                        setUpStoreList(it.data)
                        Log.d(TAG, "Data: ${it.data}")
                    }
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    Log.e(TAG, it.message.toString())
                    requireContext().toastLong(it.message!!)
                }
            }
        }
    }

    private fun setUpStoreList(data: List<Store>) {
        storeAdapter.onItemClick = {

        }

        storeAdapter.setItems(data)

        with(rvStore) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = storeAdapter
        }
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
        const val TAG = "MapsFragment"
    }

}