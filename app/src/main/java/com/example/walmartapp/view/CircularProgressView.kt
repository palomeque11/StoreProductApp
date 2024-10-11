package com.example.walmartapp.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.walmartapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CircularProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private var progressColor: Int = Color.BLUE // Default color
    private var strokeWidth: Float = 20f // Default stroke width
    private var progress: Float = 0f // Default progress (0% to 100%)

    private val paint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
    }

    private var job: Job? = null

    init {
        // Load custom attributes
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularProgressView, defStyle, 0)
        progressColor = a.getColor(R.styleable.CircularProgressView_progressColor, progressColor)
        strokeWidth = a.getDimension(R.styleable.CircularProgressView_strokeWidth, strokeWidth)
        a.recycle()

        paint.color = progressColor
        paint.strokeWidth = strokeWidth
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredSize = 200 // Set a desired size for the view
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width.coerceAtMost(desiredSize), height.coerceAtMost(desiredSize))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val radius = (width.coerceAtMost(height) / 2f) - (strokeWidth / 2f)
        val cx = width / 2f
        val cy = height / 2f


        paint.color = Color.LTGRAY // Background color
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        canvas.drawCircle(cx, cy, radius, paint)

        // Draw the progress arc
        paint.color = progressColor
        paint.style = Paint.Style.STROKE
        canvas.drawArc(
            cx - radius, cy - radius,
            cx + radius, cy + radius,
            -90f, 360f * (progress / 100f), false, paint
        )
    }

    fun startProgress() {
        // Start updating progress
        job = CoroutineScope(Dispatchers.Main).launch {
            while (progress < 100) {
                progress += 10
                invalidate()
                delay(100) // Update every 100 milliseconds
            }
            // Reset progress after reaching 100%
            resetProgress()
        }
    }

    private fun resetProgress() {
        progress = 0f // Reset progress to 0
        invalidate() // Request to redraw the view
        startProgress() // Restart progress
    }

    fun stopProgress() {
        job?.cancel()
        job = null
    }
}