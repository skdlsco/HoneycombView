package com.eka.hex

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.RelativeLayout


class HoneycombView : RelativeLayout {

    var data: ArrayList<ArrayList<CellData>> = ArrayList()

    var cells = ArrayList<HexagonView>()
    var cellDistance: Float = 0f
    var cellWidth: Float = 0f
    var cellStrokeWidth: Float = 0f


    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        getAttrs(attrs, null)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttrs(attrs, defStyleAttr)
    }

    private fun createViews() {
        data.forEachIndexed { i, list ->
            list.forEachIndexed { j, cellData ->
                val cell = HexagonView(context)
                cell.cellWidth = cellWidth
                cell.setPadding(cellDistance.toInt(), cellDistance.toInt(), cellDistance.toInt(), cellDistance.toInt())
                cell.cellData = cellData
                cell.strokeWidth = cellStrokeWidth
                cell.setOnClickListener {
                    cellData.onClick.invoke(it)
                }
                cells.add(cell)
            }
        }
    }

    private fun setIds() {
        cells.forEachIndexed { i, view ->
            view.id = i + 1
        }
    }

    private fun setInView() {
        var spos = 0
        var p = 0

        cells.forEachIndexed { i, v ->
            val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
                if (i - spos == 0) {
                    addRule(ALIGN_PARENT_TOP)
                    if (p % 2 != 0)
                        topMargin = (cellWidth / 2 + cellDistance + cellStrokeWidth).toInt()

                } else {
                    addRule(ALIGN_TOP, i)
                    topMargin = ((cellWidth + cellDistance + cellStrokeWidth).toInt())
                }

                if (p != 0) {
                    addRule(RIGHT_OF, spos)
                    leftMargin = -(cellWidth / Math.pow(3.0, 0.5) * 0.5 + cellDistance + cellStrokeWidth * 1.5).toInt()
                }
            }
            v.layoutParams = layoutParams
            addView(v)
            if (data[p].size - 1 <= i - spos) {
                spos += data[p].size
                p++
            }
        }
    }

    fun viewUpdate() {
        createViews()
        setIds()
        setInView()
    }

    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int?) {
        if (defStyleAttr == null) {
            setTypedArray(context.obtainStyledAttributes(attrs, R.styleable.HoneycombView))
        } else {
            setTypedArray(context.obtainStyledAttributes(attrs, R.styleable.HoneycombView, defStyleAttr, 0))
        }
    }

    private fun setTypedArray(typedArray: TypedArray) {
        cellDistance = typedArray.getDimension(R.styleable.HoneycombView_cellDistance, 0f)
        cellWidth = typedArray.getDimension(R.styleable.HoneycombView_cellWidth, 0f)
        cellStrokeWidth = typedArray.getDimension(R.styleable.HoneycombView_cellStrokeWidth, 0f)
        typedArray.recycle()
    }
}