package com.example.nbc__kiosk_rev2

class MainMenu {
    fun displayMenu(): Int {
        println("============= 메뉴 ==============")
        println("[1] 추천 메뉴")
        println("[2] 디저트")
        println("[-1] 키오스크 종료")
        println("원하는 메뉴를 입력해주세요.")
        print(">")

        return readLine()!!.toInt()
    }
}