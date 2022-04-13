package com.hamza.ieeechallenge.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String? = "",
    val phone: String? = "",
    val photoUrl: String? = null,
    val isOnline: Boolean = false,
    val lastSeen: Long = 0L,
    val about: String = ""
): Parcelable
