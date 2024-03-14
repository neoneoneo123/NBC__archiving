package com.example.nbc__kiosk_rev2

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Timer
import java.util.TimerTask


class BurgerAll(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        println(
            "\n[ Burgers MENU ]\n" +
                    "1. ShackBurger          | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거\n" +
                    "2. [한정판]SmokeShack     | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거 (잔여 수량 : ${smokeQuantity})\n" +
                    "3. Shroom Burger        | W 9.4 | 몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거\n" +
                    "4. Cheeseburger         | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거\n" +
                    "5. Hamburger            | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거\n" +
                    "0. 뒤로 가기              | 뒤로 가기"
        )
        print(">")
    }

    override fun order() {
        var input4 = 0
        var input5 = 0
        var tempTotalPrice = 0.0

        println(
            "\n아래와 같이 주문 하시겠습니까?  (현재 주문 대기수: ${restWating})" +
                    "\n\n" +
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
        println("W ${String.format("%.1f", tempTotalPrice)}")
        println()
        println("1. 주문      2. 메뉴판")
        print(">")
        try {
            input4 = readLine()!!.toInt()

            if (timeSearch()[0] in "23:10:00".."23:20:00") {
                println()
                println(
                    "현재는 ${timeSearch()[1]} 입니다. \n" +
                            "은행 점검 시간은 오후 11:10 ~ 오후 11:20 이므로 결제할 수 없습니다."
                )
            } else {
                when (input4) {
                    1 -> {
                        if (cash - tempTotalPrice < 0) { // 결제 잔액 부족 시
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
                        } else { // 결제 정상 진입
                            var couponReject = true

                            while (couponReject) {
                                println()
                                println("1. 쿠폰 입력하고 할인 받기      2. 그냥 결제하기      3. 돌아가기")
                                print(">")

                                try {
                                    input5 = readLine()!!.toInt()

                                    when (input5) {
                                        1 -> {
                                            var discountCheck = false

                                            println()
                                            println("할인 쿠폰을 입력해주세요.")
                                            print(">")
                                            var inputCoupon = readLine()!!.toString()

                                            if (inputCoupon == "sparta") {
                                                println()
                                                println("스파르타 회원은 10% 할인 됩니다.")
                                                tempTotalPrice = tempTotalPrice * 0.9

                                                println("[ Total ]")
                                                println(
                                                    "W ${
                                                        String.format(
                                                            "%.1f",
                                                            tempTotalPrice
                                                        )
                                                    }"
                                                )
                                                println()

                                                var balanceCash = cash - tempTotalPrice

                                                println("결제가 완료되었습니다.  ${timeSearch()[2]}")
                                                discountCheck = true

                                                print(
                                                    "+-------------------------------------+\n" +
                                                            "|                                     |\n" +
                                                            "|          SHAKESHACK BURGER          |\n" +
                                                            "|                                     |\n" +
                                                            "|  [매장명] 쉐이크쉑버거 우리집점            |\n" +
                                                            "|  [주 소] 서울 우리집구 우리집동 우리집      |\n" +
                                                            "|  [대표자] 정지연                       |\n" +
                                                            "|  [매출일] ${timeSearch()[2]}         |\n" +
                                                            "| =================================== |\n" +
                                                            "|  제품명                       금액     |\n"
                                                )

                                                cartList.forEach {
                                                    var tempName = ""
                                                    if (it.name.length > 15) tempName =
                                                        it.name.substring(0, 14) + "~"
                                                    else tempName = it.name

                                                    println(
                                                        "|  ${tempName.padEnd(15)}     ${
                                                            "%.1f".format(
                                                                it.price
                                                            ).padStart(10)
                                                        }     |"
                                                    )

                                                    //수량 제한 메뉴 수량 빼기
                                                    if(it.name == "Smoke Burger") {
                                                        smokeQuantity--
                                                    }
                                                }


                                                println(
                                                    "|                                     |"
                                                )

                                                //만약에 할인을 받았으면, 할인 여부를 표시
                                                if (discountCheck) {
                                                    println(
                                                        "|  10% 할인 적용                        |"
                                                    )
                                                }


                                                println(
                                                            "|  총매출액              ${
                                                                "%.1f".format(
                                                                    tempTotalPrice
                                                                ).padStart(10)
                                                            }     |\n" +
                                                            "|                                     |\n" +
                                                            "|             THANK YOU!         neo  |\n" +
                                                            "+-------------------------------------+\n"
                                                )

                                                println(
                                                    "현재 잔액은 ${
                                                        String.format(
                                                            "%.1f",
                                                            balanceCash
                                                        )
                                                    }W 입니다."
                                                )
                                                cash -= tempTotalPrice

                                                cartList.clear()

                                                couponReject = false

                                            } else {
                                                println("존재하지 않는 쿠폰입니다.")
                                                println("메뉴를 다시 선택해주세요")
                                            }
                                        }

                                        2 -> {
                                            var balanceCash = cash - tempTotalPrice
                                            var temp = 10.0

                                            println("결제가 완료되었습니다.  ${timeSearch()[2]}")

                                            print(
                                                "+-------------------------------------+\n" +
                                                        "|                                     |\n" +
                                                        "|          SHAKESHACK BURGER          |\n" +
                                                        "|                                     |\n" +
                                                        "|  [매장명] 쉐이크쉑버거 우리집점            |\n" +
                                                        "|  [주 소] 서울 우리집구 우리집동 우리집      |\n" +
                                                        "|  [대표자] 정지연                       |\n" +
                                                        "|  [매출일] ${timeSearch()[2]}         |\n" +
                                                        "| =================================== |\n" +
                                                        "|  제품명                       금액     |\n"
                                            )

                                            cartList.forEach {
                                                var tempName = ""
                                                if (it.name.length > 15) tempName =
                                                    it.name.substring(0, 14) + "~"
                                                else tempName = it.name

                                                println(
                                                    "|  ${tempName.padEnd(15)}     ${
                                                        "%.1f".format(
                                                            it.price
                                                        ).padStart(10)
                                                    }     |"
                                                )

                                                //수량 제한 메뉴 수량 빼기
                                                if(it.name == "Smoke Burger") {
                                                    smokeQuantity--
                                                }
                                            }


                                            println(
                                                "|                                     |\n" +
                                                        "|  총매출액               ${
                                                            "%.1f".format(
                                                                tempTotalPrice
                                                            ).padStart(10)
                                                        }    |\n" +
                                                        "|                                     |\n" +
                                                        "|             THANK YOU!         neo  |\n" +
                                                        "+-------------------------------------+\n"
                                            )

                                            println(
                                                "현재 잔액은 ${
                                                    String.format(
                                                        "%.1f",
                                                        balanceCash
                                                    )
                                                }W 입니다."
                                            )
                                            cash -= tempTotalPrice

                                            cartList.clear()

                                            couponReject = false
                                        }

                                        3 -> {
                                            println()
                                            println("메인 화면으로 돌아갑니다.")
                                            couponReject = false
                                            return
                                        }
                                    }

                                } catch (e: java.lang.NumberFormatException) {
                                    println("숫자를 입력해주세요.")
                                }
                            }
                        }
                    }

                    2 -> {
                        println()
                        println("메뉴판으로 돌아갑니다.")
                        return
                    }

                    3 -> { //결제 쿠폰

                    }

                    else -> {
                        println("메뉴를 다시 입력해주세요.")
                        print(">")
                    }
                }
            }
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

    fun timeSearch(): MutableList<String> {
        var timeList = mutableListOf<String>()
        val timer = Timer() //타이머가 따로 돌아야할 것 같은데..
        val task = object : TimerTask() {
            override fun run() {
                val currentTime = Date()
                val formattedTime = SimpleDateFormat("HH:mm:ss").format(currentTime)
                val formattedTimePrint = SimpleDateFormat("a hh:mm").format(currentTime)
                val formattedTimePrintAll =
                    SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(currentTime)

                timeList.add(formattedTime)
                timeList.add(formattedTimePrint)
                timeList.add(formattedTimePrintAll)
            }
        }

        timer.scheduleAtFixedRate(task, 0, 10)

        Thread.sleep(50)
        timer.cancel()

        return timeList
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

        if (smokeQuantity > 0) {
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
        } else {
            println()
            println("금일 준비한 수량이 모두 소진되었습니다.")
            println("다른 메뉴를 선택해주세요.")
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

