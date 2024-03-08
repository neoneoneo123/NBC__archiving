package com.example.nbc__calculator

fun main() {
    var input: Int = 0
    var addition = AddOperation()
    var substraction = SubstractOperation()
    var multiplication = MultiplyOperation()
    var division = DivideOperation()
    var modulo = ModuloOperation()
    var free = FreeOperation()

    // 계산기 소개
    println("계산기 app을 실행합니다.")
    println("================================")
    println("환영합니다! 이 계산기는 덧셈, 뺄셈, 곱셈,")
    println("나눗셈, 나머지(mod) 연산을 지원합니다.")
    println("모든 결과는 소수점 한 자리로 표현됩니다.")
    println("================================")
    println(" _____________________")
    println("|  _________________  |")
    println("| | NEO          0. | |")
    println("| |_________________| |")
    println("|  ___ ___ ___   ___  |")
    println("| | 7 | 8 | 9 | | + | |")
    println("| |___|___|___| |___| |")
    println("| | 4 | 5 | 6 | | - | |")
    println("| |___|___|___| |___| |")
    println("| | 1 | 2 | 3 | | x | |")
    println("| |___|___|___| |___| |")
    println("| | . | 0 | = | | / | |")
    println("| |___|___|___| |___| |")
    println("|_____________________|")
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
                modulo.operation()
            }
            9 -> {
                free.operation()
            }
            -1 -> {
                println("계산기를 종료합니다.")
                break
            }
            else -> {
                println("메뉴를 다시 선택해주세요.")
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
    println("[9] 자유 계산 모드")
    println("[-1] 계산기 종료")
    println("원하는 메뉴를 입력해주세요.")
    print(">")
}