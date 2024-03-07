package com.example.nbc__calculator

import java.util.Stack
import kotlin.text.StringBuilder

class FreeOperation: AbstractOperation() {

    fun getPriority(operator: Char): Int {
        return when (operator) {
            '+', '-' -> 1
            '*', '/', '%' -> 2
            else -> 0
        }
    }

    fun operationPostfix(postfixText: String): Double {
        var result = 0.0
        var stack = Stack<Double>()

        postfixText.forEach {
            when {
                it.isDigit() -> stack.push(it.toString().toDouble())
                it == '+' -> stack.push(stack.pop() + stack.pop())
                it == '-' -> {
                    val op2 = stack.pop()
                    val op1 = stack.pop()
                    stack.push(op1 - op2)
                }
                it == '*' -> stack.push(stack.pop() * stack.pop())
                it == '/' -> {
                    val op2 = stack.pop()
                    val op1 = stack.pop()
                    stack.push(op1 / op2)
                }
            }
        }

        result = stack.pop()
        println(result)

        return result
    }

    fun exchangePostFix(infixText: String): String {
        var postFixText = ""

        var output = StringBuilder()
        var operators = Stack<Char>()

        if (infixText.isNotEmpty()) {

//            for (i in 0 .. infixText.length - 1) {
//                when {
//                    //피연산자 저장 파트
//                    infixText[i].isLetterOrDigit() -> {
//
//                        //두 자리 수 이상의 연속된 숫자 처리를 위한 임시 변수
//                        var numberBuilder = StringBuilder()
//
//                        if (infixText[i].isDigit()) {
//                            numberBuilder.append(infixText[i])
//
//                            //다음 문자가 숫자인 경우도 처리함
//                            var nextIndex = infixText.indexOf(infixText[i]) + 1
//                            while (nextIndex < infixText.length && infixText[nextIndex].isDigit()) {
//                                numberBuilder.append(infixText[nextIndex])
//                                numberBuilder.append(' ')
//                                nextIndex++
//                            }
//                            if (numberBuilder.isNotEmpty()) {
//                                output.append(numberBuilder.toString())
//                            } else {
//                                output.append(infixText[i])
//                            }
//                        }
//                    }
//
//                    //연산자 저장 파트
//                    infixText[i] == '(' -> operators.push(infixText[i])
//                    infixText[i] == ')' -> {
//
//                    }
//                }
//            }

            infixText.forEach {
                when {
                    //피연산자 저장
                    it.isLetterOrDigit() -> output.append(it)

//                    it.isLetterOrDigit() -> {
//
//                        //두 자리 수 이상의 연속된 숫자들을 하나의 피연산자로 처리하기 위한 스트링빌더
//                        var numberBuilder = StringBuilder()
//
//                        if (it.isDigit()) {
//                            numberBuilder.append(it)
//
//                            //다음 문자가 숫자인 경우도 처리함
//                            var nextIndex = infixText.indexOf(it) + 1
//                            while (nextIndex < infixText.length && infixText[nextIndex].isDigit()) {
//                                numberBuilder.append(infixText[nextIndex])
//                                numberBuilder.append(' ')
//                                nextIndex++
//                            }
//
//                            if (numberBuilder.isNotEmpty()) {
//                                output.append(numberBuilder.toString())
//                            } else {
//                                output.append(it)
//                            }
//
//                            if ((nextIndex-1) < infixText.length && infixText[nextIndex-1].isDigit()) {
//                                return@forEach
//                            }
//                        }
//                    }

                    //() 괄호 저장해뒀다가 제거
                    it == '(' -> operators.push(it)
                    it == ')' -> {
                        while (operators.isNotEmpty() && operators.peek() != '(') {
                            output.append(operators.pop())
                        }
                        operators.pop() // '(' 제거
                    }
                    //연산자인 경우
                    else -> {
                        //스택에서 우선순위가 높거나 같은 연산자들을 pop하여 output에 저장 후, 현재 연산자를 스택에 push
                        while (operators.isNotEmpty() && getPriority(operators.peek()) >= getPriority(it)) {
                            output.append(operators.pop())
                        }
                        operators.push(it)
                    }
                }
            }
            //스택에 남아있는 연산자가 있을 경우 모두 output에 저장

            while (operators.isNotEmpty()) {
                output.append(operators.pop())
            }

            println(output)
            operationPostfix(output.toString())
        }

        return postFixText
    }

    override fun operation() {
        println("자유 연산 모드를 선택하셨습니다.")
        println("숫자, 연산자를 동시에 입력할 수 있습니다.")
        println("숫자는 한 자리 수만을 지원합니다.")
        println("나머지(mod) 연산은 지원하지 않습니다.")
        println("입력되는 숫자는 정수여야 합니다..")
        println("예시 1) 2+4*3.")
        println("예시 2) 10/(2+3).")
//        println("예시 3) (5-1)/2.")
        println("계산식을 입력해주세요.")
        print(">")
        this.freeText = readLine()!!.toString()

        var tempText = freeText
//        var tempText = "2*(2+3)"

        //후위계산식으로 변환하여 계산


        println("=========== 계산 결과 ============")
        println(exchangePostFix(tempText))
        println("")
    }
}