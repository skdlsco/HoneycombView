package com.eka.hex

import android.view.View


class CellData(var color: Int = 0,
               var icon: Int = 0,
               var strokeColor: Int = 0,
               var startColor: Int = 0,
               var endColor: Int = 0,
        // view 부모 pos  자식 pos
               var onClick: (View, Int, Int) -> Unit? = { _, _, _ -> }) {
}