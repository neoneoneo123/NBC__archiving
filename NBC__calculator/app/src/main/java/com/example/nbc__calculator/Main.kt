package com.example.nbc__calculator

fun main() {
    var input: Int = 0
    var cal1 = Calculator()
    var addition = AddOperation()
    var substraction = SubstractOperation()
    var multiplication = MultiplyOperation()
    var division = DivideOperation()

    // 계산기 소개
    println("계산기 app을 실행합니다.")
    println("================================")
    println("환영합니다! 이 계산기는 덧셈, 뺄셈, 곱셈,")
    println("나눗셈, 나머지(mod) 연산을 지원합니다.")
    println("모든 결과는 소수점 한 자리로 표현됩니다.")
    println("================================")
    println("")

    while (input != -1) {
        //메뉴 안내 및 선택
        displayMenu()
        input = readLine()!!.toInt()

        when (input) {
            1 -> {
                addition.operation()
            }
            2 -> {
                substraction.operation()
            }
            3 -> {
                multiplication.operation()
            }
            4 -> {
                division.operation()
            }
            5 -> {
                cal1.mod()
            }
            -1 -> {
                println("계산기를 종료합니다.")
                break
            }
        }
    }
}

fun displayMenu() {
    println("============= 메뉴 ==============")
    println("[1] 더하기")
    println("[2] 빼기")
    println("[3] 곱하기")
    println("[4] 나누기")
    println("[5] 나머지")
    println("[-1] 계산기 종료")
    println("원하는 메뉴를 입력해주세요.")
}