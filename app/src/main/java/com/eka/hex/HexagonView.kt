package com.eka.hex

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class HexagonView : View {
    var cellData = CellData()
    var cellWidth: Float = 0f
    var strokeWidth: Float = 0f
    var strokeColor = Color.BLACK

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension((paddingLeft + paddingRight + (cellWidth + strokeWidth * 2) / Math.pow(3.0, 0.5) * 2).toInt(),
                (cellWidth + paddingTop + paddingBottom + strokeWidth * 2).toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawHexagon(it)
            drawIcon(it)
        }
    }

    private fun drawIcon(canvas: Canvas) {
        if (cellData.icon == null)
            return
        val bitmap = BitmapFactory.decodeResource(resources, cellData.icon)
        canvas.drawBitmap(bitmap, (width - paddingLeft - paddingRight - bitmap.height).toFloat() / 2 + paddingLeft,
                (height - paddingBottom - paddingTop - bitmap.height).toFloat() / 2 + paddingTop, Paint().apply {
            isAntiAlias = true
        })
    }

    private fun drawHexagon(canvas: Canvas) {
        val isStroke = this@HexagonView.strokeWidth != 0f && strokeColor != 0
        var width = (height.toFloat() - paddingRight - paddingLeft) / 2f // height width로 바꾸기
        var edge = width / Math.pow(3.0, 0.5).toFloat() * 2

        var paint = Paint().apply {
            isAntiAlias = true
            color = cellData.strokeColor
            style = Paint.Style.FILL
        }

        if (isStroke) {
            val path = Path()
            path.moveTo(0f + paddingLeft, width + paddingTop)
            path.lineTo(edge / 2 + paddingLeft, width * 2 + paddingTop)
            path.lineTo(edge * 1.5f + paddingLeft, width * 2 + paddingTop)
            path.lineTo(edge * 2 + paddingLeft, width + paddingTop)
            path.lineTo(edge * 1.5f + paddingLeft, 0f + paddingTop)
            path.lineTo(edge / 2 + paddingLeft, 0f + paddingTop)
            path.lineTo(0f + paddingLeft, width + paddingTop)
            path.close()
            canvas.drawPath(path, paint)
        }

        width = (height.toFloat() - paddingRight - paddingLeft - strokeWidth * 2) / 2f // height width로 바꾸기
        edge = (width / Math.pow(3.0, 0.5) * 2).toFloat()
        edge += (this.width - edge * 2 - strokeWidth * 2) / 2
        paint = Paint().apply {
            isAntiAlias = true
            color = cellData.color
            style = Paint.Style.FILL
            if (cellData.startColor != 0 && cellData.endColor != 0) {
                val gradient = LinearGradient(0f, 0f, width * 2, edge * 2, cellData.startColor, cellData.endColor, Shader.TileMode.CLAMP)
                shader = gradient
            }
        }
        val path = Path()
        path.moveTo(0f + paddingLeft + strokeWidth, width + paddingTop + strokeWidth)
        path.lineTo(edge / 2 + paddingLeft + strokeWidth, width * 2 + paddingTop + strokeWidth)
        path.lineTo(edge * 1.5f + paddingLeft + strokeWidth, width * 2 + paddingTop + strokeWidth)
        path.lineTo(edge * 2 + paddingLeft + strokeWidth, width + paddingTop + strokeWidth)
        path.lineTo(edge * 1.5f + paddingLeft + strokeWidth, 0f + paddingTop + strokeWidth)
        path.lineTo(edge / 2 + paddingLeft + strokeWidth, 0f + paddingTop + strokeWidth)
        path.lineTo(0f + paddingLeft + strokeWidth, width + paddingTop + strokeWidth)
        path.close()
        canvas.drawPath(path, paint)
    }
}