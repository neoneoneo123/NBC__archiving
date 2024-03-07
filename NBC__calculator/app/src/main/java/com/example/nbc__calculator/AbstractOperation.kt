package com.example.nbc__calculator

abstract class AbstractOperation {

    var left: Double = 0.0
    var right: Double = 0.0
    var freeText: String = ""

    abstract fun operation()
}