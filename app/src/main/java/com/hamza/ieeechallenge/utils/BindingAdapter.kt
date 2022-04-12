package com.hamza.ieeechallenge.utils

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hamza.ieeechallenge.R
import com.hamza.ieeechallenge.data.model.CountryCallingCodes

@BindingAdapter("format_flag")
fun TextView.formatFlag(item: CountryCallingCodes) {
    val flagOffset = 0x1F1E6
    val asciiOffset = 0x41
    val codeName = item.code
    val firstChar = Character.codePointAt(codeName.toString(), 0) - asciiOffset + flagOffset
    val secondChar = Character.codePointAt(codeName.toString(), 1) - asciiOffset + flagOffset
    val flag = String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
    item.let {
        text = flag
    }
}

@BindingAdapter("bindAvatar")
fun ImageView.bindAvatar(imageUrl: String) {
    apply { Glide.with(context)
        .load(imageUrl)
        .into(this)
    }
}

@BindingAdapter("bindAvatarInit")
fun ImageView.bindAvatar(uri: Uri?) {
    apply {
        if (uri != null) {
            Glide.with(context)
                .load(uri)
                .into(this)
        } else {
            Glide.with(context)
                .load(R.drawable.ic_baseline_person_24)
                .into(this)
        }

    }

}