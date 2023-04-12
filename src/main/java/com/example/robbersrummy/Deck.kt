package com.example.robbersrummy

import com.example.robbersrummy.Card.Rank
import com.example.robbersrummy.Card.Suit
import java.util.*

class Deck {
    private val cards = mutableListOf<Card>()

    init {
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
        cards.shuffle()
    }

    fun draw(): Card? {
        if (cards.isEmpty()) {
            return null
        }
        return cards.removeAt(cards.size - 1)
    }
}
