package com.example.nbc__calculator

import java.util.Stack
import kotlin.text.StringBuilder

class FreeOperation: Operation() {
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

        //공백을 기준으로 데이터를 배열에 저장해두기
        var splitPostFix = postfixText.split(' ')

        splitPostFix.forEach {
            when {
                it == "+" -> stack.push(stack.pop() + stack.pop())
                it == "-" -> {
                    val op2 = stack.pop()
                    val op1 = stack.pop()
                    stack.push(op1 - op2)
                }
                it == "*" -> stack.push(stack.pop() * stack.pop())
                it == "/" -> {
                    val op2 = stack.pop()
                    val op1 = stack.pop()
                    stack.push(op1 / op2)
                }
                else -> {
                    stack.push(it.toString().toDouble())
                }
            }
        }
        result = stack.pop() //후위계산식 결과
        return result
    }

    fun exchangePostFix(infixText: String): String {
        var postFixText = ""

        var output = StringBuilder()
        var operators = Stack<Char>()

        if (infixText.isNotEmpty()) {

            var i = 0
            while (i < infixText.length) {
                var accCount = 0
                when {
                    //숫자 저장 파트
                    infixText[i].isLetterOrDigit() -> {
                        var numberBuiler = StringBuilder()
                        var nextIndex = i + 1
                        if (nextIndex < infixText.length && infixText[nextIndex].isDigit()) { //다음이 숫자면
                            accCount = nextIndex - 1
                            while (accCount < infixText.length && infixText[accCount].isDigit()) { //다음이 숫자가 아닐 때까지
                                numberBuiler.append(infixText[accCount])
                                accCount++
                            }
                            if (numberBuiler.isNotEmpty()) {
                                numberBuiler.append(' ')
                                output.append(numberBuiler.toString())
                            }
                            i += (accCount - i)
                            continue
                        } else { //다음이 숫자가 아니면
                            numberBuiler.append(infixText[i])

                            if (numberBuiler.isNotEmpty()) {
                                numberBuiler.append(' ')
                                output.append(numberBuiler.toString())
                            }
                        }
                    }

                    //연산자 저장 파트
                    //괄호 처리
                    infixText[i] == '(' -> operators.push(infixText[i])
                    infixText[i] == ')' -> {
                        while (operators.isNotEmpty() && operators.peek() != '(') {
                            output.append(operators.pop())
                            output.append(' ')
                        }
                        operators.pop() //'(' 제거
                    }
                    //괄호 외 연산자 처리
                    else -> {
                        //스택에서 우선순위가 높거나 같은 연산자들을 pop하여 저장 후, 현재 연산자를 스택에 push
                        while (operators.isNotEmpty() && getPriority(operators.peek()) >= getPriority(infixText[i])) {
                            output.append(operators.pop())
                            output.append(' ')
                        }
                        operators.push(infixText[i])
                    }
                }
                i++
            }

            //스택에 남아있는 연산자가 있을 경우 모두 output에 저장
            while (operators.isNotEmpty()) {
                output.append(operators.pop())
                output.append(' ')
            }

            output.deleteAt(output.lastIndex) //후위계산식의 맨 마지막 공백 제거
            postFixText = (output.toString())
        }
        return operationPostfix(postFixText).toString()
    }

    fun freeOperation(freeText: String) {
        println("=========== 계산 결과 ============")
        println(exchangePostFix(freeText))
        println("")

    }
}


