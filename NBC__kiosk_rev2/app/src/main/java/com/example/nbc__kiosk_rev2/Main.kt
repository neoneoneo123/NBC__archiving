package com.example.nbc__kiosk_rev2

var input = 0
var input2 = 0
var cartList: MutableList<Kiosk> = mutableListOf()
var cash = 100.0

fun main() {
    val burgersList = burgerDataInsert()
    val custardsList = custardDataInsert()
    val drinksList = drinkDataInsert()
    val beersList = beerDataInsert()

    println("SHAKESHACK BURGER 에 오신걸 환영합니다.")

    while (input != -1) {
        displayMenu()
        try {
            input = readLine()!!.toInt()
            when (input) {
                1 -> {
                    burgersList[0].displayInfo()
                    try {
                        input2 = readLine()!!.toInt()
                        when (input2) {
                            1 -> {
                                burgersList[input2].displayInfo()
                            }

                            2 -> {
                                burgersList[input2].displayInfo()
                            }

                            3 -> {
                                burgersList[input2].displayInfo()
                            }

                            4 -> {
                                burgersList[input2].displayInfo()
                            }

                            5 -> {
                                burgersList[input2].displayInfo()
                            }

                            0 -> {

                            }

                            else -> {
                                println("메뉴를 다시 입력해주세요.")
                            }
                        }
                    } catch (e: java.lang.NumberFormatException) {
                        println("숫자를 입력해주세요.")
                    }

                }

                2 -> {
                    custardsList[0].displayInfo()
                    try {
                        input2 = readLine()!!.toInt()
                        println(input2)
                        when (input2) {
                            1 -> {
                                custardsList[input2].displayInfo()
                            }

                            2 -> {
                                custardsList[input2].displayInfo()
                            }

                            3 -> {
                                custardsList[input2].displayInfo()
                            }

                            0 -> {

                            }

                            else -> {
                                println("메뉴를 다시 입력해주세요.")
                            }
                        }
                    } catch (e: java.lang.NumberFormatException) {
                        println("숫자를 입력하세요")
                    }
                }

                3 -> {
                    drinksList[0].displayInfo()
                    try {
                        input2 = readLine()!!.toInt()
                        println(input2)
                        when (input2) {
                            1 -> {
                                drinksList[input2].displayInfo()
                            }

                            2 -> {
                                drinksList[input2].displayInfo()
                            }

                            3 -> {
                                drinksList[input2].displayInfo()
                            }

                            4 -> {
                                drinksList[input2].displayInfo()
                            }

                            5 -> {
                                drinksList[input2].displayInfo()
                            }

                            6 -> {
                                drinksList[input2].displayInfo()
                            }

                            0 -> {

                            }

                            else -> {
                                println("메뉴를 다시 입력해주세요.")
                            }
                        }
                    } catch (e: java.lang.NumberFormatException) {
                        println("숫자를 입력하세요")
                    }
                }

                4 -> {
                    beersList[0].displayInfo()
                    try {
                        input2 = readLine()!!.toInt()
                        println(input2)
                        when (input2) {
                            1 -> {
                                beersList[input2].displayInfo()
                            }

                            2 -> {
                                beersList[input2].displayInfo()
                            }

                            3 -> {
                                beersList[input2].displayInfo()
                            }

                            4 -> {
                                beersList[input2].displayInfo()
                            }

                            5 -> {
                                beersList[input2].displayInfo()
                            }

                            6 -> {
                                beersList[input2].displayInfo()
                            }

                            0 -> {

                            }

                            else -> {
                                println("메뉴를 다시 입력해주세요.")
                            }
                        }
                    } catch (e: java.lang.NumberFormatException) {
                        println("숫자를 입력하세요")
                    }
                }

                //주문하기
                5 -> {
                    if (cartList.isNotEmpty()) {
                       burgersList[0].order()
                    }
                }

                //취소하기
                6 -> {
                    if (cartList.isNotEmpty()) {
                        burgersList[0].cancel()
                    }
                }

                0 -> {
                    println("프로그램을 종료합니다.")
                    return
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("숫자를 입력해주세요.")
        }


    }
}

fun displayMenu() {
    println(
        "\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n" +
                "\n" +
                "[ SHAKESHACK MENU ]\n" +
                "1. Burgers         | 앵거스 비프 통살을 다져만든 버거\n" +
                "2. Forzen Custard  | 매장에서 신선하게 만드는 아이스크림\n" +
                "3. Drinks          | 매장에서 직접 만드는 음료\n" +
                "4. Beer            | 뉴욕 브루클린 브루어리에서 양조한 맥주\n" +
                "0. 키오스크 종료하기"
    )
    if (cartList.isEmpty()) {
        print(">")
    }

    if (cartList.isNotEmpty()) {
        println(
            "\n [ ORDER MENU ]\n" +
                    "5. Order       | 장바구니를 확인 후 주문합니다.\n" +
                    "6. Cancel      | 진행중인 주문을 취소합니다.\n"
        )
        print(">")
    }
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

fun custardDataInsert(): List<Kiosk> {
    val c0 = CustardAll(9998, "all", 0.0, "all")
    val c1 = ClassicHandSpunShakes(1, "Classic Hand-Spun Shakes", 6.5, "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크")
    val c2 = Floats(2, "Floats", 6.5, "부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료")
    val c3 = CupAndCones(3, "Cup & Cones", 5.4, "매일 점포에서 신선하게 제조하는 쫀득하고 진한 아이스크림")

    val custards = listOf(c0, c1, c2, c3)
    return custards
}

fun drinkDataInsert(): List<Kiosk> {
    val d0 = DrinkAll(9997, "all", 0.0, "all")
    val d1 = Lemonade(1, "Lemonade", 4.3, "매장에서 직접 만드는 상큼한 레몬에이드")
    val d2 = FreshBrewedIcedTea(2, "Fresh Brewed Iced Tea", 3.5, "직접 유기농 홍차를 우려낸 아이스 티")
    val d3 = Fifty(3, "Fifty", 3.8, "레몬에이드와 유기농 홍차를 우려낸 아이스 티가 만나 탄생한 쉐이크쉑의 시그니처 음료")
    val d4 = FountainSoda(4, "Fountain Soda", 2.9, "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프, 환타 파인애플")
    val d5 = AbitaRootBeer(5, "Abita Root Beer", 5.4, "청량감 있는 독특한 미국식 무알콜 탄산음료")
    val d6 = HotTea(5, "Hot Tea", 3.4, "보성 유기농 찻잎을 우려낸 녹차, 홍차, 페퍼민트 & 레몬그라스")

    val drinks = listOf(d0, d1, d2, d3, d4, d5, d6)
    return drinks
}

fun beerDataInsert(): List<Kiosk> {
    val be0 = BeerAll(9996, "all", 0.0, "all")
    val be1 =
        ShackMeisterAle(1, "ShackMeister Ale", 9.8, "쉐이스쉑 버거를 위해 뉴욕 브루클린 브루어리에서 특별히 양조한 예일 맥주")
    val be2 = PaleAle(2, "Pale Ale, Draft", 6.8, "Magpie Brewing Co.")
    val be3 = Porter(3, "Porter, Draft", 6.8, "Magpie Brewing Co.")
    val be4 = Wheat(4, "Wheat, Draft", 6.8, "Magpie Brewing Co.")
    val be5 = IPA(5, "IPA, Can", 6.8, "The Hand and Malt")
    val be6 = MochaStout(5, "Mocha Stout, Can", 6.8, "The Hand and Malt")

    val beers = listOf(be0, be1, be2, be3, be4, be5, be6)
    return beers
}