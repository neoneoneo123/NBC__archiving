package com.example.nbc__kiosk_rev2

class BeerAll(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        println(
            "[ Beers MENU ]\n" +
                    "1. ShackMeister Ale    | W 9.8 | 쉐이스쉑 버거를 위해 뉴욕 브루클린 브루어리에서 특별히 양조한 예일 맥주\n" +
                    "2. Pale Ale, Draft     | W 6.8 | Magpie Brewing Co.\n" +
                    "3. Porter, Draft       | W 6.8 | Magpie Brewing Co.\n" +
                    "4. Wheat, Dreft        | W 6.8 | Magpie Brewing Co.\n" +
                    "5. IPA, Can            | W 6.8 | The Hand and Malt\n" +
                    "6. Mocha Stout, Can    | W 6.8 | The Hand and Malt\n" +
                    "0. 뒤로가기              | 뒤로가기"
        )
        print(">")
    }

    override fun order() {}

    override fun cancel() {}

}

class ShackMeisterAle(
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

class PaleAle(
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

class Porter(
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

class Wheat(
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

class IPA(
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

class MochaStout(
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