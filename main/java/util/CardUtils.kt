package com.example.robbersrummy

import android.content.Context
import androidx.core.content.ContextCompat


object CardUtils {
    private const val cardWidth = 140
    private const val cardHeight = 190

    fun getAllCards(): List<String> {
        val suits = listOf("S", "C", "D", "H")
        val values = (1..13).map { it.toString() }

        return suits.flatMap { suit -> values.map { value -> "$value$suit" } }
    }

    fun getCardCoordinates(card: String): Pair<Int, Int> {
        val suit = card[card.length - 1]
        val rankString = card.substring(0, card.length - 1)

        val rank = when (rankString) {
            "A" -> 1
            "K" -> 13
            "Q" -> 12
            "J" -> 11
            else -> rankString.toInt()
        }

        val xOffset = ((rank - 1) % 13) * cardWidth
        val yOffset = when (suit) {
            'S' -> 0
            'C' -> cardHeight
            'D' -> 2 * cardHeight
            'H' -> 3 * cardHeight
            else -> throw IllegalArgumentException("Invalid suit: $suit")
        }

        return Pair(xOffset, yOffset)
    }

    fun getCardBackCoordinates(): Pair<Int, Int> {
        val x = -1862
        val y = -1
        return Pair(x, y)
    }
}