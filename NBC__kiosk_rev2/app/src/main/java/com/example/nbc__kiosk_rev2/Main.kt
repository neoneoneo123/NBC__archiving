package com.example.nbc__kiosk

import com.example.nbc__kiosk_rev2.DessertMenu
import com.example.nbc__kiosk_rev2.MainMenu
import com.example.nbc__kiosk_rev2.RecommendMenu

var mainMenu = MainMenu()
var recommend = RecommendMenu()
var dessert = DessertMenu()

fun main() {
    //가게 입장 : 첫 실행 시 점원이 반겨주는 화면
    println("키오스크 app을 실행합니다.")
    println("================================")
    println("안녕하세요! 롯데리아 입니다.")
    println("================================")

    var input: Int = 0
    while (input != -1) {
        when (mainMenu.displayMenu()) {
            1 -> {
                recommend.displaySubMenuRecommend()
            }
            2 -> {
                dessert.displaySubMenuDessert()
            }
            -1 -> {
                println("키오스크를 종료합니다.")
                break
            }
            else -> {
                println("메뉴를 다시 선택해주세요.")
            }
        }
    }
}





