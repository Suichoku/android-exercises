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
    private var state: Int = 0 // amount of values given

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clearClicked(view: View?) {
        value1 = ""
        value2 = ""
        state = 0
        valueTextView.text = getString(R.string.value)
    }

    fun buttonClicked(view: View?) {
        val num = (view as Button).text.toString()
        var temp = when (state) {
            0 -> value1
            1 -> value2
            else -> "" // clear value
        }

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

        if(state == 0 || state == 2) {
            value1 = temp
        } else value2 = temp

    }

    fun plusClicked(view: View?) {
        when(state) {
            0 -> { // first addition
                state = 1
            }
            1 -> { // plus already once clicked (or more)
                if(value2 == "") { // to avoid bug when pressing plus two times in a row
                    value2 = "0.0" // give default value
                }
                value1 = "${value1.toDouble() + value2.toDouble()}"
                valueTextView.text = value1
                value2 = ""
                state = 1

            }
            else -> {
                value1 = valueTextView.text.toString()
                state = 1
            }
        }
        //valueTextView.text = getString(R.string.value)
    }

    fun equalsClicked(view: View?) {
        if(value1 != "" && value2 != "") {
            val df = DecimalFormat("#.#####")
            valueTextView.text = df.format(value1.toDouble() + value2.toDouble())
            value1 = ""
            value2 = ""
            state = 0
        }
    }
}
