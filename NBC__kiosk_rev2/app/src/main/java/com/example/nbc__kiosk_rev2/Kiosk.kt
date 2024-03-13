package com.example.nbc__kiosk_rev2

abstract class Kiosk {
    abstract var id: Int
    abstract var name: String
    abstract var price: Double
    abstract var des: String

    abstract fun displayInfo()
    abstract fun order()
}