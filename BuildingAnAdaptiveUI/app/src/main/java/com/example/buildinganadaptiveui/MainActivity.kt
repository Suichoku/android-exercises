package com.example.buildinganadaptiveui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


data class Employee(var firstName: String, var lastName: String, var jobTitle: String)
val employees = arrayOf(Employee("Renato", "Ksenia", "District Quality Coordinator"),
    Employee("Rosangela", "Metzli", "International Intranet Representative"),
    Employee("Tim", "Asuncion", "District Intranet Administrator"),
    Employee("Bartol", "Zemfina", "Dynamic Research Manager"),
    Employee("Jeannette", "Giang", "Central Infrastructure Consultant")
)
var tabletSize = false

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // https://stackoverflow.com/questions/9279111/determine-if-the-device-is-a-smartphone-or-tablet
        tabletSize = resources.getBoolean(R.bool.isTablet)
        if(tabletSize) {
            oneTextView.text = getString(R.string._1) + "\n${employees[0].firstName} ${employees[0].lastName}"
            twoTextView.text = getString(R.string._2) + "\n${employees[1].firstName} ${employees[1].lastName}"
            threeTextView.text = getString(R.string._3) + "\n${employees[2].firstName} ${employees[2].lastName}"
            fourTextView.text = getString(R.string._4) + "\n${employees[3].firstName} ${employees[3].lastName}"
            fiveTextView.text = getString(R.string._5) + "\n${employees[4].firstName} ${employees[4].lastName}"
        }

        // show first employee data
        showEmployeeData(0)
    }

    private fun showEmployeeData(index: Int) {
        firstnameTextView.text = employees[index].firstName
        lastnameTextView.text = employees[index].lastName
        jobtitleTextView.text = employees[index].jobTitle
        employeeInfoTextView.text = if(tabletSize) {
            getString(R.string.basic_text_2)
        } else getString(R.string.basic_text)


        // image
        val id = when(index) {
            1    -> R.drawable.employee2
            2    -> R.drawable.employee3
            3    -> R.drawable.employee4
            4    -> R.drawable.employee5
            else -> R.drawable.employee1
        }

        imageView.setImageResource(id)
    }

    fun numberClicked(view: View?) {
        val text = (view as TextView).text.toString().substring(0,1)
        showEmployeeData(text.toInt() - 1)
    }
}
