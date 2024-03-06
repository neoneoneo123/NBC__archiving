package com.example.nbc__calculator

open class Calculator {
    var left: Double = 0.0
    var right: Double = 0.0

    init {
        //println("계산기 생성자 실행")
    }

    open fun operation () {

    }

    fun mod () {
        println("나머지를 선택하셨습니다.")
        println("[좌] % [ ] 입력해주세요.")
        this.left = readLine()!!.toDouble()
        println("[ ] % [우] 입력해주세요.")
        this.right = readLine()!!.toDouble()

        println("=========== 계산 결과 ============")
        println("${this.left} % ${this.right} = ${left % right}")
        println("")
    }
}