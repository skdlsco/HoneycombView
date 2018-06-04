package com.eka.hex

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View


class HexaView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)

        setMeasuredDimension(width,
                (paddingTop + paddingBottom + (width - paddingLeft - paddingRight) / Math.pow(3.0, 0.5) * 2).toInt())
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("asdf", "$width   $height")
        canvas?.let {
            drawHexagon(it)
            drawIcon(it)
        }
    }

    private fun drawIcon(canvas: Canvas) {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_action_name)
        canvas.drawBitmap(bitmap, (width - paddingLeft - paddingRight - bitmap.width).toFloat() / 2 + paddingLeft,
                (height - paddingTop - paddingBottom - bitmap.height).toFloat() / 2, Paint().apply {
            isAntiAlias = true
        })
    }

    private fun drawHexagon(canvas: Canvas) {
        val paint: Paint = Paint().apply {
            isAntiAlias = true
            color = Color.BLUE
        }
        val width = (width.toFloat() - paddingRight - paddingLeft) / 2f
        Log.e("Asdfasfs", "$width")
        val path = Path()
        val edge = width / Math.pow(3.0, 0.5).toFloat() * 2
        path.moveTo(width + paddingLeft, 0f + paddingTop)
        path.lineTo(width * 2 + paddingLeft, edge / 2 + paddingTop)
        path.lineTo(width * 2 + paddingLeft, edge * 1.5f + paddingTop)
        path.lineTo(width + paddingLeft, edge * 2 + paddingTop)
        path.lineTo(0f + paddingLeft, edge * 1.5f + paddingTop)
        path.lineTo(0f + paddingLeft, edge / 2 + paddingTop)
        path.lineTo(width + paddingLeft, 0f + paddingTop)
        path.close()
        canvas.run {
            drawPath(path, paint)
        }
    }

    inner class Hexa : View(context) {

    }
}