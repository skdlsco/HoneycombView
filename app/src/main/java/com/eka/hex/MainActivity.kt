package com.eka.hex

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        honeycombView.data.add(ArrayList<CellData>().apply {
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
                startColor = Color.parseColor("#fbae17")
                endColor = Color.parseColor("#fbcc1e")
            })
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
            })
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
            })
        })
        honeycombView.data.add(ArrayList<CellData>().apply {
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
            })
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
            })
        })
        honeycombView.data.add(ArrayList<CellData>().apply {
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
            })
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
            })
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
            })
        })
        honeycombView.data.add(ArrayList<CellData>().apply {
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
            })
            add(CellData().apply {
                icon = R.drawable.ic_action_name
                color = Color.BLACK
                strokeColor = Color.BLUE
            })
        })
        honeycombView.viewUpdate()
    }
}
