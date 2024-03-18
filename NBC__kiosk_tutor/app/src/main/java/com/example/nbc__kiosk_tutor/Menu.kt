package com.example.nbc__kiosk_tutor

open class Menu(name: String, description: String) {
    var name: String
    var description: String

    init {
        this.name = name
        this.description = description
    }

    open fun displayInfo() {
        println("이름: $name, 설명: [ $description ]")
    }
}