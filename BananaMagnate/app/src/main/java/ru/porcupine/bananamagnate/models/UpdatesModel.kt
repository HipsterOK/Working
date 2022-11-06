package ru.porcupine.bananamagnate.models

class UpdatesModel(
    var name: String? = null,
    var price:Int = 0,
    var listener: (() -> Unit)? = null
){
    fun setAction(func:() -> Unit){
        listener = func
    }

    fun getAction(){
        listener
    }
}