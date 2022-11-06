package ru.porcupine.pizzadeliveryguy

class CarAdapter {
    fun fillCars(): MutableList<CarsModel> {
        val carList: MutableList<CarsModel> = mutableListOf()
        carList.add(CarsModel(R.drawable.car1))
        carList.add(CarsModel(R.drawable.car2))
        carList.add(CarsModel(R.drawable.car3))
        carList.add(CarsModel(R.drawable.car4))
        carList.add(CarsModel(R.drawable.car5))
        carList.add(CarsModel(R.drawable.car6))
        carList.add(CarsModel(R.drawable.car7))
        carList.add(CarsModel(R.drawable.car8))
        carList.add(CarsModel(R.drawable.car9))
        carList.add(CarsModel(R.drawable.car10))

        return carList
    }
}