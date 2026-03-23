package com.example.myapplication

import kotlin.random.Random

class JokenpoEngine {
    private val plays = listOf("Pedra", "Tesoura", "Papel")

    enum class Result {
        WIN,
        DRAW,
        LOSS
    }

    private fun getAIPlay(): String {
        return plays[Random.nextInt(0, plays.size)]
    }

    fun calculateResult(selectedPlay: String): Result {
        val normalizedSelectedPlay = selectedPlay.trim()
        val aiPlay = getAIPlay()

        if (normalizedSelectedPlay == aiPlay) {
            return Result.DRAW
        }

        return when (normalizedSelectedPlay) {
            "Pedra" -> if (aiPlay == "Tesoura") Result.WIN else Result.LOSS
            "Tesoura" -> if (aiPlay == "Papel") Result.WIN else Result.LOSS
            "Papel" -> if (aiPlay == "Pedra") Result.WIN else Result.LOSS
            else -> Result.DRAW
        }
    }
}
