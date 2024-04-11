package com.example.nbc__neomarket.itemData

data class Item (
    val id: String,
    val image: Int,
    val title: String,
    val description: String,
    val seller: String,
    val price: Int,
    val address: String,
    val like: Int,
    val chat: Int,
)