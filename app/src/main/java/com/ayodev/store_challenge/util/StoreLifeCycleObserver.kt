package com.ayodev.store_challenge.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ayodev.store_challenge.R

class StoreLifecycleObserver(private val registry : ActivityResultRegistry)
    : DefaultLifecycleObserver {
    private lateinit var getContent : ActivityResultLauncher<String>

    private lateinit var imageViewContext: Context

    private lateinit var activity: ComponentActivity

    private lateinit var setUriImage: (input: Uri?) -> Unit

    private lateinit var imageView: ImageView

    override fun onCreate(owner: LifecycleOwner) {
        getContent = registry.register("key", owner, ActivityResultContracts.GetContent()) {
            setUriImage(it)

//            Glide.with(imageViewContext)
//                .load(
//                    it ?: ContextCompat.getDrawable(activity, R.drawable.placeholder_add_image)
//                )
//                .transform(
//                    CenterCrop(),
//                    RoundedCorners(activity.resources.getDimensionPixelOffset(R.dimen.form_radius))
//                )
//                .into(imageView)
        }
    }

    fun selectImage(
        context: Context,
        activity: ComponentActivity,
        setUriImage: (input: Uri?) -> Unit,
        imageView: ImageView
    ) {
        imageViewContext = context
        this.activity = activity
        this.setUriImage = setUriImage
        this.imageView = imageView

        getContent.launch("image/*")
    }
}