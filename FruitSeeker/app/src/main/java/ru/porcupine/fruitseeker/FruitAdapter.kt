package ru.porcupine.fruitseeker

class FruitAdapter {
    fun fillListFruit(): MutableList<FruitModel> {
        var listFruitModel:MutableList<FruitModel> = arrayListOf()

        listFruitModel.add(FruitModel(0, R.drawable.img1))
        listFruitModel.add(FruitModel(1, R.drawable.img2))
        listFruitModel.add(FruitModel(2, R.drawable.img3))
        listFruitModel.add(FruitModel(3, R.drawable.img4))
        listFruitModel.add(FruitModel(4, R.drawable.img5))
        listFruitModel.add(FruitModel(5, R.drawable.img6))
        listFruitModel.add(FruitModel(6, R.drawable.img7))
        listFruitModel.add(FruitModel(7, R.drawable.img8))
        listFruitModel.add(FruitModel(8, R.drawable.img9))
        listFruitModel.add(FruitModel(9, R.drawable.img10))
        listFruitModel.add(FruitModel(10, R.drawable.img11))
        listFruitModel.add(FruitModel(11, R.drawable.img12))

        return listFruitModel
    }
}