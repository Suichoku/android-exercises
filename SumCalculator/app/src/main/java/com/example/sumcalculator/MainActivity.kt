package com.example.sumcalculator

import android.icu.text.DecimalFormat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var value1: String = "" // holds first value
    private var value2: String = "" // holds second value
    private var state: Boolean = false // current state of calculation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clearClicked(view: View?) { // reset everything
        value1 = ""
        value2 = ""
        state = false
        valueTextView.text = getString(R.string.value)
    }

    fun buttonClicked(view: View?) { // number or symbol clicked (sign change/dot)
        val num = (view as Button).text.toString()
        var temp = if(!state) {
            value1
        } else value2

        if(temp.length >= 11) return // check if value length is too high

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

        valueTextView.text = temp // show current value

        if(!state) { // save current value
            value1 = temp
        } else value2 = temp

    }

    fun plusClicked(view: View?) {
        if(!state) {
            value1 = if(value1 == "-" || value1 == "." || value1 == "-.") "0.0" else value1 // default value when user gives invalid value
            state = true // change calculation to second state
        } else {
            if(value2 == "" || value2 == "-" || value2 == "." || value2 == "-.") { // to avoid bug when pressing plus two times in a row
                value2 = "0.0" // give default value
            }
            if(value1.length >= 13) return // check if sum value is too large
            value1 = "${value1.toDouble() + value2.toDouble()}" // save current sum to value1
            valueTextView.text = value1
            value2 = "" // reset value2
        }

        //valueTextView.text = getString(R.string.value) // reset current shown value to default value
    }

    fun equalsClicked(view: View?) {
        value2 = if(value2 == "-" || value2 == "." || value2 == "-.") "0.0" else value2 // default value when user gives invalid value
        if(value1 != "" && value2 != "") {
            val df = DecimalFormat("#.#####") // formatting pattern
            valueTextView.text = df.format(value1.toDouble() + value2.toDouble()) // show resulting sum
            value1 = "" // reset value1
            value2 = "" // reset value2
            state = false // reset calculation state
        }
    }
}
