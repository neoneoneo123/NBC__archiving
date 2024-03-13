package com.example.nbc__kiosk_rev2

class CustardAll(
    override var id: Int,
    override var name: String,
    override var price: Double,
    override var des: String
) : Kiosk() {
    override fun displayInfo() {
        println("[ Custards MENU ]\n" +
                "1. Classic Hand-Spun Shakes   | W 6.5 | 쫀득하고 진한 커스터드가 들어간 클래식 쉐이크\n" +
                "2. Floats                     | W 6.5 | 부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료\n" +
                "3. Cup & Cones                | W 5.4 | 매일 점포에서 신선하게 제조하는 쫀득하고 진한 아이스크림 \n" +
                "0. 뒤로가기                     | 뒤로가기"
        )
        print(">")
    }

    override fun order() {
        TODO("Not yet implemented")
    }

}

class ClassicHandSpunShakes(
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

class Floats(
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

class CupAndCones(
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