package com.example.nbc__calculator

class SubstractOperation: Operation() {
    override fun operation (left: Double, right: Double) {
        println("=========== 계산 결과 ============")
        println("${left} - ${right} = ${left - right}")
        println("")
    }
}