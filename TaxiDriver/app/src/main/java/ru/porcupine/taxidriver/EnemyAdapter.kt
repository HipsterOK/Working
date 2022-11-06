package ru.porcupine.taxidriver

class EnemyAdapter {
    fun fillEnemyes(): MutableList<Enemyes> {
        val carList: MutableList<Enemyes> = mutableListOf()
        carList.add(Enemyes(R.drawable.cars_1up, true))
        carList.add(Enemyes(R.drawable.cars_2up, true))
        carList.add(Enemyes(R.drawable.cars_3up, true))
        carList.add(Enemyes(R.drawable.cars_4up, true))
        carList.add(Enemyes(R.drawable.cars_1down, false))
        carList.add(Enemyes(R.drawable.cars_2down, false))
        carList.add(Enemyes(R.drawable.cars_3down, false))
        carList.add(Enemyes(R.drawable.cars_4down, false))
        carList.shuffle()
        return carList
    }
}
