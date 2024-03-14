package com.example.nbc__calculator

abstract class AbstractOperation {

    var freeText: String = ""

    abstract fun operation(left: Double, right: Double)

}