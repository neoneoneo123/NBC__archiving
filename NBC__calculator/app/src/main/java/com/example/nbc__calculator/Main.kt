package com.example.nbc__calculator

import java.lang.ArithmeticException

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

        var left: Double = Double.NaN
        var right: Double = Double.NaN

        try {
        input = readLine()!!.toInt() //메뉴 선택 받기
            when (input) {
                1 -> {
                    println("더하기를 선택하셨습니다.")
                    do {
                        try {
                            println("[좌] + [ ] 입력해주세요.")
                            print(">")
                            left = readLine()!!.toDouble()
                        } catch (e: java.lang.NumberFormatException) {
                            println("숫자를 입력해주세요.")
                        }
                    } while (left.isNaN())

                    do {
                        try {
                            println("[ ] + [우] 입력해주세요.")
                            print(">")
                            right = readLine()!!.toDouble()
                            addition.operation(left, right)
                        } catch (e: java.lang.NumberFormatException) {
                            println("숫자를 입력해주세요.")
                        }
                    } while (right.isNaN())
                }
                2 -> {
                    println("빼기를 선택하셨습니다.")
                    do {
                        try {
                            println("[좌] + [ ] 입력해주세요.")
                            print(">")
                            left = readLine()!!.toDouble()
                        } catch (e: java.lang.NumberFormatException) {
                            println("숫자를 입력해주세요.")
                        }
                    } while (left.isNaN())

                    do {
                        try {
                            println("[ ] + [우] 입력해주세요.")
                            print(">")
                            right = readLine()!!.toDouble()
                            substraction.operation(left, right)
                        } catch (e: java.lang.NumberFormatException) {
                            println("숫자를 입력해주세요.")
                        }
                    } while (right.isNaN())
                }
                3 -> {
                    println("곱하기를 선택하셨습니다.")
                    do {
                        try {
                            println("[좌] + [ ] 입력해주세요.")
                            print(">")
                            left = readLine()!!.toDouble()
                        } catch (e: java.lang.NumberFormatException) {
                            println("숫자를 입력해주세요.")
                        }
                    } while (left.isNaN())

                    do {
                        try {
                            println("[ ] + [우] 입력해주세요.")
                            print(">")
                            right = readLine()!!.toDouble()
                            multiplication.operation(left, right)
                        } catch (e: java.lang.NumberFormatException) {
                            println("숫자를 입력해주세요.")
                        }
                    } while (right.isNaN())
                }
                4 -> {
                    println("나누기를 선택하셨습니다.")
                    do {
                        try {
                            println("[좌] + [ ] 입력해주세요.")
                            print(">")
                            left = readLine()!!.toDouble()
                        } catch (e: java.lang.NumberFormatException) {
                            println("숫자를 입력해주세요.")
                        }
                    } while (left.isNaN())

                    do {
                        try {
                            println("[ ] + [우] 입력해주세요.")
                            print(">")
                            right = readLine()!!.toDouble()

                            if (right == 0.0) {
                                throw ArithmeticException("0으로 나눌 수 없습니다. 다시 입력해주세요.")
                            }

                            division.operation(left, right)
                        } catch (e: ArithmeticException) {
                            println("0으로 나눌 수 없습니다. 다른 숫자를 입력해주세요.")
                        } catch (e: java.lang.NumberFormatException) {
                            println(e.message)
                        }
                    } while (right.isNaN() || right == 0.0)
                }
                5 -> {
                    println("나머지를 선택하셨습니다.")

                    do {
                        try {
                            println("[좌] + [ ] 입력해주세요.")
                            print(">")
                            left = readLine()!!.toDouble()
                        } catch (e: java.lang.NumberFormatException) {
                            println("숫자를 입력해주세요.")
                        }
                    } while (left.isNaN())

                    do {
                        try {
                            println("[ ] + [우] 입력해주세요.")
                            print(">")
                            right = readLine()!!.toDouble()

                            if (right == 0.0) {
                                throw ArithmeticException("0으로 나눌 수 없습니다. 다시 입력해주세요.")
                            }

                            modulo.operation(left, right)
                        } catch (e: ArithmeticException) {
                            println("0으로 나눌 수 없습니다. 다른 숫자를 입력해주세요.")
                        } catch (e: java.lang.NumberFormatException) {
                            println(e.message)
                        }
                    } while (right.isNaN() || right == 0.0)
                }
                9 -> {
                    println("자유 연산 모드를 선택하셨습니다.")
                    println("숫자, 연산자를 동시에 입력할 수 있습니다.")
                    println("나머지(mod) 연산은 지원하지 않습니다.")
                    println("입력되는 숫자는 정수여야 합니다..")
                    println("예시 1) 2+4*3.")
                    println("예시 2) 80*(200+300).")
                    println("계산식을 입력해주세요.")
                    print(">")
                    var freeText = readLine()!!.toString()
                    free.freeOperation(freeText)
                }
                -1 -> {
                    println("계산기를 종료합니다.")
                    break
                }
                else -> {
                    println("메뉴를 다시 선택해주세요.")
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("숫자를 입력해주세요.")
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