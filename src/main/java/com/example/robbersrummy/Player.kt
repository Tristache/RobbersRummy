package com.example.robbersrummy

class Player(val id: Int) {
    val hand = mutableListOf<Card>()

    fun addCardToHand(card: Card) {
        hand.add(card)
    }
}
