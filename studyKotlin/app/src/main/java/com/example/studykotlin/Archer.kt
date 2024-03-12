package com.example.studykotlin

class Archer : Characters {
    var name:String //="" 로 초기화 하지 않음. 생성자가 있어서 객체가 만들어질 때 초기화 되기 때문임
    var age:Int
    var gender:String
    var money:Int
    var hp:Int
    var weapons:MutableList<String>

    constructor(_name:String, _age:Int, _gender:String, _money:Int, _hp:Int) {
        weapons = mutableListOf<String>()
        name = _name
        age = _age
        gender = _gender
        money = _money
        hp = _hp
        println("${name}궁수 생성")
    }

    override fun attack() {
        println("활쏘기!")
    }

    fun windArrow() {
        println("바람의 화살!!")
    }

    fun windJump(destination:String) {
        println("${destination}까지 도약!")
    }
}