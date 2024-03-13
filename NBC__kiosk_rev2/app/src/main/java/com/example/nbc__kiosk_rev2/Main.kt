package com.example.nbc__kiosk_rev2

var input = 0
var input2 = 0

fun main() {
    var burgersList = burgerDataInsert()
    println("SHAKESHACK BURGER 에 오신걸 환영합니다.")

    while(input != -1) {
        displayMenu()
        input = readLine()!!.toInt()

        when(input) {
            1 -> {
                burgersList[0].displayInfo()
                input2 = readLine()!!.toInt()
                println(input2)
                when (input2) {
                    1 -> {
                        println("init")
                        burgersList[1].displayInfo()
                    }
                    2 -> {

                    }
                }
            }
            2 -> {

            }
            3 -> {

            }
            4 -> {

            }
            0 -> {
                println("프로그램을 종료합니다.")
                return
            }
        }
    }
}

fun displayMenu() {
    println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
    println("[ SHAKESHACK MENU ]")
    println("1. Burgers         | 앵거스 비프 통살을 다져만든 버거")
    println("2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림")
    println("3. Drinks          | 매장에서 직접 만드는 음료")
    println("4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주")
    println("0. 종료             | 프로그램 종료")
}

fun burgerDataInsert(): List<Kiosk> {
    val b0 = BurgerAll(9999, "all", 0.0, "all")
    val b1 = ShackBurger(1, "Shack Burger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거")
    val b2 = SmokeShack(2, "Smoke Burger", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거")
    val b3 = ShroomBurger(3, "Shroom Burger", 9.4, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거")
    val b4 = Cheeseburger(4, "Cheese Burger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거")
    val b5 = Hamburger(5, "Ham Burger", 6.9, "비프패티를 기반으로 야채가 들어간 기본버거")

    val burgers = listOf(b0, b1, b2, b3, b4, b5)

    return burgers
}