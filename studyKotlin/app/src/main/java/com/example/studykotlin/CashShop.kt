package com.example.studykotlin

class CashShop private constructor() {
    private val bowPrice = 150
    private val staffPrice = 120

    companion object { //클래스 내부에 선언되어, 해당 클래스와 관련된 팩토리 메소드나 다른 정적 멤버를 담을 수 있는 특별한 객체임
        @Volatile private var instance: CashShop? = null
        //Volatile은 해당 변수가 항상 메인 메모리에서 쓰이도록 보장함
        //instance에는 캐시샵 클래스의 인스턴스를 저장함

        fun getInstance(): CashShop {
            //외부에서 요청이 왔을 때 인스턴스가 널인지 검증
            if (instance == null) {
                //싱크로나이즈드로 외부 스레드의 접근을 막음
                //스레드간 객체상태 혼돈을 막기위해 사용
                synchronized(this) {
                    instance = CashShop()
                }
            }
            return instance!!
        }
    }

    fun purchaseWeapon(characters:Characters) {
        if(characters is Archer) {
            characters?.run {
                if (money >= bowPrice) {
                    println("[구매 후 금액]: [${money} - ${bowPrice}] = ${money - bowPrice}")
                    money -= bowPrice
                    weapons.add("슈퍼 활")
                } else {
                    println("돈이 부족합니다")
                }
            }
        } else if (characters is Wizard) {
            characters?.run {
                if (money >= staffPrice) {
                    println("[구매 후 금액]: [${money} - ${staffPrice}] = ${money - staffPrice}")
                    money -= staffPrice
                    weapons.add("슈퍼 스태프")
                } else {
                    println("돈이 부족합니다")
                }
            }
        }
    }
}