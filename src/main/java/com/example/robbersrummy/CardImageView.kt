package com.example.robbersrummy

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

class CardImageView(context: Context) : AppCompatImageView(context) {
    var cardX: Int = 0
    var cardY: Int = 0

    private val cardsDrawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.cards)

    override fun onDraw(canvas: Canvas) {
        cardsDrawable?.setBounds(0, 0, width, height)
        cardsDrawable?.state = intArrayOf(cardX, cardY)
        cardsDrawable?.draw(canvas)
    }
}
