package ru.porcupine.gravityguy.controllers

class GameController {
    val playerController = PlayerController()
    val enemyController = EnemyController()
    var score = 0
    var gameEnbl = true
}