package com.example.mycalculator

class Calculator {
    var left: Double = 0.0
    var right: Double = 0.0

    init {
        //println("계산기 생성자 실행")
    }

    fun addition () {
        println("더하기를 선택하셨습니다.")
        println("좌항을 입력해주세요.")
        this.left = readLine()!!.toDouble()
        println("우항을 입력해주세요.")
        this.right = readLine()!!.toDouble()

        println("======== 계산 결과 =========")
        println("${this.left} + ${this.right} = ${left + right}")
        println("")
    }

    fun subtraction () {
        println("빼기를 선택하셨습니다.")
        println("좌항을 입력해주세요.")
        this.left = readLine()!!.toDouble()
        println("우항을 입력해주세요.")
        this.right = readLine()!!.toDouble()

        println("======== 계산 결과 =========")
        println("${this.left} - ${this.right} = ${left - right}")
        println("")
    }

    fun multiplication () {
        println("곱하기를 선택하셨습니다.")
        println("좌항을 입력해주세요.")
        this.left = readLine()!!.toDouble()
        println("우항을 입력해주세요.")
        this.right = readLine()!!.toDouble()

        println("======== 계산 결과 =========")
        println("${this.left} * ${this.right} = ${left * right}")
        println("")
    }

    fun division () {
        println("나누기를 선택하셨습니다.")
        println("좌항을 입력해주세요.")
        this.left = readLine()!!.toDouble()
        println("우항을 입력해주세요.")
        this.right = readLine()!!.toDouble()

        println("======== 계산 결과 =========")
        println("${this.left} / ${this.right} = ${left / right}")
        println("")
    }
}