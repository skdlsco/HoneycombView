package com.eka.hex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        honeycombView.data.add(ArrayList<CellData>().apply {
            add(CellData())
            add(CellData())
            add(CellData())
            add(CellData())
        })

        honeycombView.data.add(ArrayList<CellData>().apply {
            add(CellData())
            add(CellData())
            add(CellData())
        })

        honeycombView.data.add(ArrayList<CellData>().apply {
            add(CellData())
            add(CellData())
            add(CellData())
            add(CellData())
        })

        honeycombView.data.add(ArrayList<CellData>().apply {
            add(CellData())
            add(CellData())
            add(CellData())
        })
        honeycombView.viewUpdate()
    }
}
