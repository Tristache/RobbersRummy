package com.example.robbersrummy

class Game(val numberOfPlayers: Int, val numberOfRounds: Int) {
    private val players = mutableListOf<Player>()
    private val deck = Deck()
    private var currentPlayerIndex = 0

    init {
        for (i in 1..numberOfPlayers) {
            players.add(Player(i))
        }
        dealCards()
    }

    private fun dealCards() {
        for (player in players) {
            for (i in 1..14) {
                val card = deck.draw()
                if (card != null) {
                    player.addCardToHand(card)
                }
            }
        }
    }

    fun playTurn() {
        // Implémenter la logique du jeu ici
        // Exemple: currentPlayer.drawCard(), currentPlayer.playCard(), etc.
    }

    fun nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numberOfPlayers
    }

    fun isGameOver(): Boolean {
        // Implémenter la logique pour vérifier si la partie est terminée
    }
}
