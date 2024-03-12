package com.example.nbc__kiosk_rev2

class RecommendMenu {
    fun displaySubMenuRecommend() {
        var subInput:Int = 0
        var name:String = ""
        var price:Int = 0

        println("=========== 하위 메뉴 ============")
        println("[1] 왕돈까스버거[NEW]        |   7,500   | 압도적인 크기의 K-style 왕돈까스버거 ")
        println("[2] 매운 왕돈까스버거[NEW]    |   7,500   | 압도적인 크기의 매콤한 왕돈까스버거")
        println("[3] 전주 비빔라이스 버거       |   6.900   | 전주비빔밥을 담은 새로운 라이스 버거")
        println("[-1] 뒤로 가기              | 뒤로 가기")
        println("원하는 메뉴를 입력해주세요.")
        print(">")

        subInput = readLine()!!.toInt()

        while (subInput != -1) {
            when (subInput) {
                1 -> {
                    println("왕돈까스버거[NEW]를 선택하셨습니다.")
                    name = "왕돈까스버거"
                    price = 7500
                    return
                }
                2 -> {
                    println("매운 왕돈까스버거[NEW]를 선택하셨습니다.")
                    name = "매운왕돈까스버거"
                    price = 7500
                    return
                }
                3 -> {
                    println("전주 비빔라이스 버거를 선택하셨습니다.")
                    name = "전주비빔라이스버거"
                    price = 6900
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