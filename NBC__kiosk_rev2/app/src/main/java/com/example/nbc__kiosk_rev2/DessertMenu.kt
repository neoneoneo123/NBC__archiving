package com.example.nbc__kiosk_rev2

class DessertMenu {
    fun displaySubMenuDessert() {
        var subInput: Int = 0
        var name:String = ""
        var price:Int = 0

        println("=========== 하위 메뉴 ============")
        println("[1] 깡돼후 돼지후라이드       |   6,500   | 은은한 갈비맛이 이색적인 돼지후라이드")
        println("[2] 포테이토               |   1,800   | 바로 튀겨낸 바삭바삭한 후렌치 포테이토")
        println("[3] 양념감자               |   2,300   | 시즈닝(오니언, 치즈, 칠리)을 한가지를 선택해 뿌려먹는 포테이토")
        println("[-1] 키오스크 종료")
        println("원하는 메뉴를 입력해주세요.")
        print(">")

        subInput = readLine()!!.toInt()

        while (subInput != -1) {
            when (subInput) {
                1 -> {
                    println("깡돼후 돼지후라이드를 선택하셨습니다.")
                    name = "깡돼후돼지후라이드"
                    price = 6500
                    return
                }
                2 -> {
                    println("포테이토를 선택하셨습니다.")
                    name = "포테이토"
                    price = 1800
                    return
                }
                3 -> {
                    println("양념감자를 선택하셨습니다.")
                    name = "양념감자"
                    price = 2300
                    return
                }
                -1 -> {
                    println("뒤로 가기")
                }
                else -> {
                    println("메뉴를 다시 선택해주세요.")
                }
            }
        }
    }
}