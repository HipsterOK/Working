package ru.porcupine.gravityguy.models

class PlayerModel(
    var x: Float = 0f,
    var rotationY:Float = 180f,
    var side: Side = Side.LEFT
)
enum class Side{
    LEFT, RIGHT
}