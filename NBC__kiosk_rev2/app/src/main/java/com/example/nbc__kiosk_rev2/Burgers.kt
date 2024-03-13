package com.example.nbc__kiosk_rev2

class BurgerAll(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        println("[ Burgers MENU ]\n" +
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
        TODO("Not yet implemented")
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

        println("${name} - ${price} - ${des}")
        println("위 메뉴를 장바구니에 추가하시겠습니까?")
        println("1. 확인        2. 취소")
        input3 = readLine()!!.toInt()

        when (input3) {
            1 -> {
                println("메뉴를 장바구니에 추가합니다.")
            }
            2 -> {
                println("메뉴 담기를 취소합니다.")
            }
            else -> {
                println("메뉴를 다시 입력해주세요.")
            }
        }
    }

    override fun order() {
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

        println("${name} - ${price} - ${des}")
        println("위 메뉴를 장바구니에 추가하시겠습니까?")
        println("1. 확인        2. 취소")
        input3 = readLine()!!.toInt()

        when (input3) {
            1 -> {
                println("메뉴를 장바구니에 추가합니다.")
            }
            2 -> {
                println("메뉴 담기를 취소합니다.")
            }
            else -> {
                println("메뉴를 다시 입력해주세요.")
            }
        }
    }

    override fun order() {
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

        println("${name} - ${price} - ${des}")
        println("위 메뉴를 장바구니에 추가하시겠습니까?")
        println("1. 확인        2. 취소")
        input3 = readLine()!!.toInt()

        when (input3) {
            1 -> {
                println("메뉴를 장바구니에 추가합니다.")
            }
            2 -> {
                println("메뉴 담기를 취소합니다.")
            }
            else -> {
                println("메뉴를 다시 입력해주세요.")
            }
        }
    }

    override fun order() {
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

        println("${name} - ${price} - ${des}")
        println("위 메뉴를 장바구니에 추가하시겠습니까?")
        println("1. 확인        2. 취소")
        input3 = readLine()!!.toInt()

        when (input3) {
            1 -> {
                println("메뉴를 장바구니에 추가합니다.")
            }
            2 -> {
                println("메뉴 담기를 취소합니다.")
            }
            else -> {
                println("메뉴를 다시 입력해주세요.")
            }
        }
    }

    override fun order() {
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

        println("${name} - ${price} - ${des}")
        println("위 메뉴를 장바구니에 추가하시겠습니까?")
        println("1. 확인        2. 취소")
        input3 = readLine()!!.toInt()

        when (input3) {
            1 -> {
                println("메뉴를 장바구니에 추가합니다.")
            }
            2 -> {
                println("메뉴 담기를 취소합니다.")
            }
            else -> {
                println("메뉴를 다시 입력해주세요.")
            }
        }
    }

    override fun order() {
        TODO("Not yet implemented")
    }
}

