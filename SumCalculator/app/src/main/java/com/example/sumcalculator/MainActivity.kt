package com.example.sumcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var value1: String = "" // holds first value
    private var value2: String = "" // holds second value
    private var state: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClicked(view: View?) {
        val num = (view as Button).text.toString()
        if(!state) {
            value1 = if(num == getString(R.string.sign)) { // is button clicked sign change
                if(value1.contains("-")) { // is value negative
                    value1.replace("-", "") // change value to positive
                } else {
                    "-$value1" // change value to negative
                }
            } else if (num == getString(R.string.dot)) { // is button clicked dot button
                if(value1.contains(".")) { // does value already contain decimal dot
                    value1 // do nothing
                } else {
                    "$value1$num" // add decimal dot
                }
            } else {
                "$value1$num" // append number to value
            }
            valueTextView.text = value1
        } else {
            value2 += num
            valueTextView.text = value2
        }
    }
}
