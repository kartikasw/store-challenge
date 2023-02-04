package com.ayodev.store_challenge.presentation.visit

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.ayodev.store_challenge.R
import com.ayodev.store_challenge.core.domain.model.Store
import com.ayodev.store_challenge.databinding.ActivityVisitBinding
import com.ayodev.store_challenge.presentation.detail_store.DetailStoreActivity
import com.ayodev.store_challenge.util.*
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class VisitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVisitBinding

    private val viewModel: VisitViewModel by viewModels()

    private lateinit var currentPhotoPath: String
    private var getFile: File? = null

    private lateinit var store: Store

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storeId = intent.getIntExtra("store_id", 0)

        setUpToolbar()
        setUpLocationStatus()
        setUpLoading()

        val locationPermission = LocationPermission(this) {
            it.accessFineLocationGranted.observe(this) { boolean ->
                if(boolean) {
                    viewModel.getCurrentLocation(store.latitude, store.longitude)
                }
            }
        }

        if(storeId == 0) {
            onBackPressedDispatcher.onBackPressed()
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                store = viewModel.getStoreById(storeId)
                launch(Dispatchers.Main) {
                    setUpStoreInfo()
                }
            }
        }

        binding.vBtnLocation.setOnClickListener {
            locationPermission.requestPermissions()
            if(locationPermission.shouldShowRationale()) {
                toastLong(resources.getString(R.string.rationale_location))
            }
        }

        binding.vBtnCamera.setOnClickListener {
            if (!allPermissionsGranted()) {
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
                if(allPermissionsGranted()) {
                    startTakePhoto()
                }
            } else {
                startTakePhoto()
            }
        }

        binding.vBtnVisit.setOnClickListener {
            val bitmap = viewModel.bitmap.value
            if(bitmap == null) {
                toastLong(resources.getString(R.string.rationale_visit))
            } else {
                val time = getCurrentTime()
                try {
                    showLoading(true)
                    viewModel.updateStoreWhenVisit(store.id, true, time, bitmap)
                    showLoading(false)
                    val intent = Intent(this, DetailStoreActivity::class.java)
                    intent.putExtra("store_id", store.store_id)
                    startActivity(intent)
                } catch (e: Exception) {
                    showLoading(false)
                    toastLong(e.toString())
                }
            }
        }

        binding.vBtnReset.setOnClickListener {
            viewModel.updateLocationState(false)
            binding.vTvLocation.text = resources.getString(R.string.state_location_empty)
            if(viewModel.bitmap.value != null) {
                Glide.with(binding.vIvImage.context)
                    .load(ContextCompat.getDrawable(this, R.drawable.placeholder_image))
                    .into(binding.vIvImage)
            }
        }

        binding.vBtnNavigation.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${store.latitude},${store.longitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            try {
                startActivity(mapIntent)
            } catch (e: ActivityNotFoundException) {
                toastShort(resources.getString(R.string.error_application_not_found))
            }
        }

        binding.vBtnNotVisit.setOnClickListener {
            try {
                showLoading(true)
                viewModel.updateStoreVisit(store.id, false)
                showLoading(false)
                onBackPressedDispatcher.onBackPressed()
            } catch (e: Exception) {
                showLoading(false)
                toastLong(e.toString())
            }
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.vToolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpLocationStatus() {
        viewModel.locationState.observe(this) {
            val text = if(it) {
                resources.getString(R.string.state_location_ok)
            } else {
                resources.getString(R.string.state_location_problem)
            }
            binding.vTvLocation.text = text
        }
    }

    private fun setUpStoreInfo() {
        binding.apply {
            vTvName.text = store.store_name
            vTvType.text = ""
            vTvDisplay.text = ""
            vTvSubDisplay.text = ""
            vTvErtm.text = ""
            vTvPareto.text = ""
            vTvEMerchandising.text = ""
            vIvImage.setImageBitmap(store.image)

            val text = if (store.visit) {
                resources.getString(R.string.last_visit, dateFormat.format(store.visit_date))
            } else {
                resources.getString(R.string.state_not_visit_yet)
            }
            val styledText = text.styledText()
            vTvVisit.text = styledText
        }
    }

    private fun setUpLoading() {
        viewModel.loading.observe(this) {
            if(it) {
                showLoading(true)
            } else {
                showLoading(false)
            }
        }
    }

    private fun showLoading(status: Boolean) {
        binding.vProgress.isVisible = status
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        createCustomTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@VisitActivity,
                "com.ayodev.store_challenge.presentation.activity.visit",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile

            val result = BitmapFactory.decodeFile(getFile?.path)
            viewModel.updateBitmap(result)
            binding.vIvImage.setImageBitmap(result)
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                this.toastLong("Tidak mendapatkan permission")
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

}