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
        var temp = if(state) {
            value2
        } else value1

        temp = if(num == getString(R.string.sign)) { // is button clicked sign change
            if(temp.contains("-")) { // is value negative
                temp.replace("-", "") // change value to positive
            } else {
                "-$temp" // change value to negative
            }
        } else if (num == getString(R.string.dot)) { // is button clicked dot button
            if(temp.contains(".")) { // does value already contain decimal dot
                temp // do nothing
            } else {
                "$temp$num" // add decimal dot
            }
        } else {
            "$temp$num" // append number to value
        }

        valueTextView.text = temp

        if(state) {
            value2 = temp
        } else value1 = temp

    }
}
