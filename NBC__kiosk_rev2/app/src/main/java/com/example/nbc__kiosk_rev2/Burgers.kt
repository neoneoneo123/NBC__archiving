package com.example.nbc__kiosk_rev2

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Timer
import java.util.TimerTask
import java.util.logging.SimpleFormatter
import kotlin.concurrent.thread

class BurgerAll(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        println(
            "\n[ Burgers MENU ]\n" +
                    "1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거\n" +
                    "2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거\n" +
                    "3. Shroom Burger | W 9.4 | 몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거\n" +
                    "4. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거\n" +
                    "5. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거\n" +
                    "0. 뒤로가기        | 뒤로가기"
        )
        print(">")
    }

    override fun order() {
        var input4 = 0
        var tempTotalPrice = 0.0

        println(
            "\n아래와 같이 주문 하시겠습니까?\n" +
                    "\n" +
                    "[ Orders ]"
        )

        if (cartList.isNotEmpty()) {
            cartList.forEach {
                tempTotalPrice += it.price
                println("${it.name} - ${it.price} - ${it.des}")
            }
        }

        println()
        println("[ Total ]")
        println("W $tempTotalPrice")
        println()
        println("1. 주문      2. 메뉴판")
        print(">")

        try {
            input4 = readLine()!!.toInt()

            val timer = Timer()
            val task = object : TimerTask() {
                override fun run() {
                    val currentTime = Date()
                    val sdf = SimpleDateFormat("HH:mm:ss")
                    val sdfPrint = SimpleDateFormat("a hh:mm")
                    val sdfPrintAll = SimpleDateFormat("yyyy-MM-DD HH:mm:ss")
                    val formattedTime = sdf.format(currentTime)
                    val formattedTimePrint = sdfPrint.format(currentTime)
                    val formattedTimePrintAll = sdfPrintAll.format(currentTime)

                    if (formattedTime in "23:10:00".."23:20:00") {
                        println()
                        println(
                            "현재는 ${formattedTimePrint} 입니다. \n" +
                                    "은행 점검 시간은 오후 11:10 ~ 오후 11:20 이므로 결제할 수 없습니다."
                        )
                    } else {
                        when (input4) {
                            1 -> {
                                if (cash - tempTotalPrice < 0) {
                                    var insufficientCash = -(cash - tempTotalPrice)
                                    println()
                                    println(
                                        "현재 잔액은 ${
                                            String.format(
                                                "%.1f",
                                                cash
                                            )
                                        }W으로 ${
                                            String.format(
                                                "%.1f",
                                                insufficientCash
                                            )
                                        }W이 부족해서 주문할 수 없습니다."
                                    )
                                } else {
                                    var balanceCash = cash - tempTotalPrice

                                    println()
                                    println("결제가 완료되었습니다.  ${formattedTimePrintAll}")

                                    println()
                                    println("현재 잔액은 ${String.format("%.1f", balanceCash)}W 입니다.")
                                    cash -= tempTotalPrice

                                    cartList.clear()
                                }
                            }

                            2 -> {
                                println()
                                println("메뉴판으로 돌아갑니다.")
                                return
                            }

                            else -> {
                                println("메뉴를 다시 입력해주세요.")
                                print(">")
                            }
                        }
                    }
                }
            }

            timer.scheduleAtFixedRate(task, 0, 1000)

            Thread.sleep(10)
            timer.cancel()
        } catch (e: java.lang.NumberFormatException) {
            println("숫자를 입력해주세요.")
        }
    }

    override fun cancel() {
        var input5 = 0

        println(
            "\n어떤 주문을 취소 하시겠습니까?\n" +
                    "\n" +
                    "[ Orders ]"
        )

        if (cartList.isNotEmpty()) {
            var i = 1
            cartList.forEach {
                println("${i}. ${it.name} - ${it.price} - ${it.des}")
                i++
            }
        }
        println("0. 돌아가기")
        print(">")

        try {
            input5 = readLine()!!.toInt()

            when (input5) {
                0 -> {
                    println()
                    println("메뉴판으로 돌아갑니다.")
                    return
                }

                else -> {
                    if (input5 > cartList.size) {
                        println("메뉴를 다시 입력해주세요.")
                        print(">")
                    } else {
                        cartList.removeAt(input5 - 1)

                        println()
                        println("주문 취소 중입니다.")
                        var job = GlobalScope.launch {
                            delay(3000)
                            //println("처리 중...")
                        }
                        runBlocking {
                            job.join()
                        }

                        println()
                        println("선택하신 메뉴을 주문 취소하였습니다.")
                    }
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("숫자를 입력해주세요.")
        }
    }

}

class ShackBurger(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        var input3 = 0

        println()
        println("${name} - ${price} - ${des}")
        println()
        println(
            "위 메뉴를 장바구니에 추가하시겠습니까?\n" +
                    "1. 확인        2. 취소"
        )
        print(">")

        try {
            input3 = readLine()!!.toInt()

            when (input3) {
                1 -> {
                    println()
                    println("메뉴를 장바구니에 추가합니다.")
                    cartList.add(this)
                }

                2 -> {
                    println()
                    println("메뉴 담기를 취소합니다.")
                }

                else -> {
                    println()
                    println(
                        "메뉴를 다시 입력해주세요.\n" +
                                ">"
                    )
                    print(">")
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("숫자를 입력해주세요.")
        }
    }

    override fun order() {

    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}

class SmokeShack(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        var input3 = 0

        println()
        println("${name} - ${price} - ${des}")
        println()
        println(
            "위 메뉴를 장바구니에 추가하시겠습니까?\n" +
                    "1. 확인        2. 취소"
        )
        print(">")

        try {
            input3 = readLine()!!.toInt()

            when (input3) {
                1 -> {
                    println()
                    println("메뉴를 장바구니에 추가합니다.")
                    cartList.add(this)
                }

                2 -> {
                    println()
                    println("메뉴 담기를 취소합니다.")
                }

                else -> {
                    println()
                    println(
                        "메뉴를 다시 입력해주세요.\n" +
                                ">"
                    )
                    print(">")
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("숫자를 입력해주세요.")
        }
    }

    override fun order() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}

class ShroomBurger(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        var input3 = 0

        println()
        println("${name} - ${price} - ${des}")
        println()
        println(
            "위 메뉴를 장바구니에 추가하시겠습니까?\n" +
                    "1. 확인        2. 취소"
        )
        print(">")

        try {
            input3 = readLine()!!.toInt()

            when (input3) {
                1 -> {
                    println()
                    println("메뉴를 장바구니에 추가합니다.")
                    cartList.add(this)
                }

                2 -> {
                    println()
                    println("메뉴 담기를 취소합니다.")
                }

                else -> {
                    println()
                    println(
                        "메뉴를 다시 입력해주세요.\n" +
                                ">"
                    )
                    print(">")
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("숫자를 입력해주세요.")
        }
    }

    override fun order() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}

class Cheeseburger(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        var input3 = 0

        println()
        println("${name} - ${price} - ${des}")
        println()
        println(
            "위 메뉴를 장바구니에 추가하시겠습니까?\n" +
                    "1. 확인        2. 취소"
        )
        print(">")

        try {
            input3 = readLine()!!.toInt()

            when (input3) {
                1 -> {
                    println()
                    println("메뉴를 장바구니에 추가합니다.")
                    cartList.add(this)
                }

                2 -> {
                    println()
                    println("메뉴 담기를 취소합니다.")
                }

                else -> {
                    println()
                    println(
                        "메뉴를 다시 입력해주세요.\n" +
                                ">"
                    )
                    print(">")
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("숫자를 입력해주세요.")
        }
    }

    override fun order() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}

class Hamburger(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        var input3 = 0

        println()
        println("${name} - ${price} - ${des}")
        println()
        println(
            "위 메뉴를 장바구니에 추가하시겠습니까?\n" +
                    "1. 확인        2. 취소"
        )
        print(">")

        try {
            input3 = readLine()!!.toInt()

            when (input3) {
                1 -> {
                    println()
                    println("메뉴를 장바구니에 추가합니다.")
                    cartList.add(this)
                }

                2 -> {
                    println()
                    println("메뉴 담기를 취소합니다.")
                }

                else -> {
                    println()
                    println(
                        "메뉴를 다시 입력해주세요.\n" +
                                ">"
                    )
                    print(">")
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("숫자를 입력해주세요.")
        }
    }

    override fun order() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}

