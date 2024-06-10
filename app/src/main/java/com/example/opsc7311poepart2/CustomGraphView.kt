package com.example.opsc7311poepart2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomGraphView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var minDuration: Long = 0
    private var maxDuration: Long = 0
    private var totalMinutesLogged: Long = 0

    private val paint = Paint()

    fun setData(minDuration: Long, maxDuration: Long, totalMinutesLogged: Long) {
        this.minDuration = minDuration
        this.maxDuration = maxDuration
        this.totalMinutesLogged = totalMinutesLogged
        invalidate() // Request a redraw
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Calculate dimensions
        val width = width.toFloat()
        val height = height.toFloat()
        val padding = 50f

        // Draw background
        paint.color = Color.WHITE
        canvas.drawRect(0f, 0f, width, height, paint)

        // Draw axis
        paint.color = Color.BLACK
        paint.strokeWidth = 5f
        canvas.drawLine(padding, height - padding, width - padding, height - padding, paint) // X-axis
        canvas.drawLine(padding, padding, padding, height - padding, paint) // Y-axis

        // Draw goal lines
        val maxBarHeight = height - 2 * padding
        val minY = height - padding - (minDuration.toFloat() / maxDuration * maxBarHeight)
        val maxY = height - padding - maxBarHeight
        val totalY = height - padding - (totalMinutesLogged.toFloat() / maxDuration * maxBarHeight)

        paint.color = Color.RED
        canvas.drawLine(padding, minY, width - padding, minY, paint) // Min duration line

        paint.color = Color.GREEN
        canvas.drawLine(padding, maxY, width - padding, maxY, paint) // Max duration line

        paint.color = Color.BLUE
        val cappedTotalY = if (totalMinutesLogged > maxDuration) padding else totalY
        canvas.drawLine(padding, cappedTotalY, width - padding, cappedTotalY, paint) // Actual duration line

        // Draw indicator if totalMinutesLogged exceeds maxDuration
        if (totalMinutesLogged > maxDuration) {
            paint.color = Color.BLACK
            paint.textSize = 40f
            val text = "Above max goal"
            canvas.drawText(text, width - padding - paint.measureText(text), padding - 10, paint)
            paint.strokeWidth = 3f
            canvas.drawLine(width - padding, cappedTotalY, width - padding - 30, cappedTotalY - 30, paint) // Arrow
            canvas.drawLine(width - padding, cappedTotalY, width - padding - 30, cappedTotalY + 30, paint) // Arrow
        }
    }
}
