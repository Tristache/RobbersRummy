package com.example.robbersrummy

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import com.example.robbersrummy.Card
import util.CardUtils
import com.example.robbersrummy.Deck
import com.example.robbersrummy.Game
import com.example.robbersrummy.Player
import com.example.robbersrummy.view.CardImageView

class MainActivity : AppCompatActivity() {
    private lateinit var cardContainer: LinearLayout
    private lateinit var deckImageView: ImageView
    private lateinit var game: Game

    companion object {
        const val CARD_WIDTH = 169
        const val CARD_HEIGHT = 245
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = Game(numberOfPlayers = 2, numberOfRounds = 6)

        // Distribuez les cartes aux joueurs
        game.startRound()

        // Affichez les cartes sur le plateau de jeu
        displayCardsOnBoard(game.tableCards)

        cardContainer = findViewById(R.id.card_container)
        deckImageView = createDeckImageView()
        cardContainer.addView(deckImageView)

        deckImageView.setOnClickListener {
            drawRandomCard()
        }
    }

    private fun createCardImageView(card: Card): ImageView {
        val imageView = ImageView(this)
        imageView.setImageResource(card.imageResId) // Remplacez ceci par la ressource d'image correspondant à la carte actuelle.
        imageView.layoutParams = ViewGroup.LayoutParams(CARD_WIDTH, CARD_HEIGHT) // Définissez la largeur et la hauteur des cartes ici.
        imageView.id = View.generateViewId()
        imageView.setOnClickListener { onCardClick(imageView) }
        return imageView
    }

    private fun displayCardsOnBoard(cards: List<Card>) {
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout1)

        // Supprimez toutes les vues précédentes du GridLayout
        gridLayout.removeAllViews()

        // Ajoutez chaque carte sous forme d'ImageView au GridLayout
        for (card in cards) {
            val cardImageView = createCardImageView(card)
            gridLayout.addView(cardImageView)
        }
    }

    private fun onCardClick(view: ImageView) {
        // Gérer le clic sur une carte
    }

    private fun createDeckImageView(): CardImageView {
        return CardImageView(this).apply {
            val (cardBackX, cardBackY) = CardUtils.getCardBackCoordinates()
            cardX = cardBackX
            cardY = cardBackY
            layoutParams = LinearLayout.LayoutParams(
                resources.getDimension(R.dimen.card_width).toInt(),
                resources.getDimension(R.dimen.card_height).toInt()
            ).apply {
                setMargins(
                    resources.getDimension(R.dimen.card_margin).toInt(),
                    resources.getDimension(R.dimen.card_margin).toInt(),
                    resources.getDimension(R.dimen.card_margin).toInt(),
                    resources.getDimension(R.dimen.card_margin).toInt()
                )
            }
        }
    }


    private fun drawRandomCard() {
        val deck = game.currentPlayer.deck
        if (deck.isNotEmpty()) {
            val randomCard = deck.drawCard()

            val cardImageView = CardImageView(this)
            val (cardX, cardY) = CardUtils.getCardCoordinates(randomCard)
            cardImageView.cardX = cardX
            cardImageView.cardY = cardY

            val layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.card_width),
                resources.getDimensionPixelSize(R.dimen.card_height)
            )
            layoutParams.setMargins(resources.getDimensionPixelSize(R.dimen.card_margin), 0, 0, 0)
            cardImageView.layoutParams = layoutParams

            cardContainer.addView(cardImageView)
        }
    }
}
