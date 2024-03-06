package com.example.nbc__calculator

class DivideOperation: AbstractOperation() {
    override fun operation () {
        println("나누기를 선택하셨습니다.")
        println("[좌] / [ ] 입력해주세요.")
        print(">")
        this.left = readLine()!!.toDouble()
        println("[ ] / [우] 입력해주세요.")
        print(">")
        this.right = readLine()!!.toDouble()

        println("=========== 계산 결과 ============")
        println("${this.left} / ${this.right} = ${left / right}")
        println("")
    }
}