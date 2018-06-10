package com.eka.hex

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class HexagonView : View {
    var cellData = CellData()
    var cellWidth: Float = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(cellWidth.toInt() + paddingLeft + paddingRight,
                (paddingTop + paddingBottom + (cellWidth) / Math.pow(3.0, 0.5) * 2).toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawHexagon(it)
            drawIcon(it)
        }
    }

    private fun drawIcon(canvas: Canvas) {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_action_name)
        canvas.drawBitmap(bitmap, (width - paddingLeft - paddingRight - bitmap.width).toFloat() / 2 + paddingLeft,
                (height - paddingTop - paddingBottom - bitmap.height).toFloat() / 2 + paddingTop, Paint().apply {
            isAntiAlias = true
        })
    }

    private fun drawHexagon(canvas: Canvas) {
        val width = (width.toFloat() - paddingRight - paddingLeft) / 2f
        val edge = width / Math.pow(3.0, 0.5).toFloat() * 2
        val paint: Paint = Paint().apply {
            isAntiAlias = true
            color = Color.BLUE
        }
        val path = Path()
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
}