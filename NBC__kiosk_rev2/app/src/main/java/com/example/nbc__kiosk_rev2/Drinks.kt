package com.example.nbc__kiosk_rev2

class DrinkAll(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        println(
            "[ Drinks MENU ]\n" +
                    "1. Lemonade                 | W 4.3 | 매장에서 직접 만드는 상큼한 레몬에이드\n" +
                    "2. Fresh Brewed Iced Tea    | W 3.5 | 직접 유기농 홍차를 우려낸 아이스 티\n" +
                    "3. Fifty                    | W 3.8 | 레몬에이드와 유기농 홍차를 우려낸 아이스 티가 만나 탄생한 쉐이크쉑의 시그니처 음료\n" +
                    "4. Fountain Soda            | W 2.9 | 코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프, 환타 파인애플\n" +
                    "5. Abita Root Beer          | W 5.4 | 청량감 있는 독특한 미국식 무알콜 탄산음료\n" +
                    "6. Hot Tea                  | W 3.4 | 보성 유기농 찻잎을 우려낸 녹차, 홍차, 페퍼민트 & 레몬그라스\n" +
                    "0. 뒤로가기                   | 뒤로가기"
        )
        print(">")
    }

    override fun order() {}

    override fun cancel() {}

}

class Lemonade(
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

class FreshBrewedIcedTea(
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

class Fifty(
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

class FountainSoda(
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

class AbitaRootBeer(
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

class HotTea(
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
