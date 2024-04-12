package com.example.nbc__neomarket.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item (
    val id: String,
    val image: Int,
    val title: String,
    val description: String,
    val seller: String,
    val price: Int,
    val address: String,
    var like: Int,
    val chat: Int,
    var isLike: Boolean,
) : Parcelable