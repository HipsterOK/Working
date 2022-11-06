package ru.porcupine.smallhelicopter.controllers

class GameController {
    val playerController = PlayerController()
    val enemyController = EnemyController()
    var score = 0
    var gameEnbl = true
}