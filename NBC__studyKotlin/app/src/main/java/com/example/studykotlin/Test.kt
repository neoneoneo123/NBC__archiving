package com.example.studykotlin

fun main() {
    // 불마법사로 객체화
    var magicanOne = Character("불마법사", "red", 180.2)
    println("${magicanOne.name}의 머리색상은 ${magicanOne.hairColor}입니다.")
    magicanOne.fireBall()
}

open class Character {
    var name:String = ""
    var hairColor:String = ""
    var height:Double = 0.0
    var age:Int = 0
    var gender:String = ""

    //명시적 생성자
    constructor(_name:String, _hairColor:String, _height:Double) {
        println("${_name}을 생성자로 넘겼어요")
        println("${_hairColor}을 생성자로 넘겼어요")
        println("${_height}을 생성자로 넘겼어요")
        name = _name
        hairColor = _hairColor
        height = _height
    }

    open fun fireBall() {
        println("파이어볼!")
    }

    fun compositing (device1:String, device2:String) :String {
        var device3 = device1 + device2
        println("새로운 무기인 ${device3}입니다.")
        return device3
    }
}