package com.example.mycalculator

fun main() {
//    var left: Double = 0.0
//    var right: Double = 0.0
    var input: Int = 0
    var cal1 = Calculator()

    // 계산기 소개
    println("계산기 app을 실행합니다.")
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    println("환영합니다! 이 계산기는 덧셈, 뺄셈, 곱셈, 나눗셈, 나머지(mod) 연산을 지원합니다.")
    println("모든 결과는 소수점 한 자리로 표현됩니다.")
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    println("")


    while (input != -1) {
        //메뉴 안내 및 선택
        displayMenu()
        input = readLine()!!.toInt()

        when (input) {
            1 -> {
                cal1.addition()
            }
            2 -> {
                cal1.subtraction()
            }
            3 -> {
                cal1.multiplication()
            }
            4 -> {
                cal1.division()
            }
            5 -> {

            }
            -1 -> {
                println("계산기를 종료합니다.")
                break
            }
        }
    }

}

fun displayMenu() {
    println("========== 메뉴 ===========")
    println("[1] 더하기")
    println("[2] 빼기")
    println("[3] 곱하기")
    println("[4] 나누기")
    println("[5] 나머지")
    println("[-1] 계산기 종료")
    println("원하는 메뉴를 입력해주세요.")
}