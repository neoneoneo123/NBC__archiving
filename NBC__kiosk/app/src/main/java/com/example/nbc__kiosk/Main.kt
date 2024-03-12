package com.example.nbc__kiosk

var input: Int = 0

fun main() {
    //가게 입장 : 첫 실행 시 점원이 반겨주는 화면
    println("키오스크 app을 실행합니다.")
    println("================================")
    println("안녕하세요! 롯데리아 입니다.")
    println("================================")

    while (input != -1) {
        when (displayMenu()) {
            1 -> {
                displaySubMenuRecommend()
            }
            2 -> {
                displaySubMenuDesert()
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

fun displayMenu(): Int {
    println("============= 메뉴 ==============")
    println("[1] 추천 메뉴")
    println("[2] 디저트")
    println("[-1] 키오스크 종료")
    println("원하는 메뉴를 입력해주세요.")
    print(">")

    return readLine()!!.toInt()
}

fun displaySubMenuRecommend() {
    var subInput: Int = 0
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
                return
            }
            2 -> {
                println("매운 왕돈까스버거[NEW]를 선택하셨습니다.")
                return
            }
            3 -> {
                println("전주 비빔라이스 버거를 선택하셨습니다.")
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

fun displaySubMenuDesert() {
    var subInput: Int = 0
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
                return
            }
            2 -> {
                println("포테이토를 선택하셨습니다.")
                return
            }
            3 -> {
                println("양념감자를 선택하셨습니다.")
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