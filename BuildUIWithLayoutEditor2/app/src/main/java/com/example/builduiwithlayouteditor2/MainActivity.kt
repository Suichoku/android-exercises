package com.example.builduiwithlayouteditor2

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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // show first employee data
        showEmployeeData(0)
    }

    private fun showEmployeeData(index: Int) {
        firstnameTextView.text = employees[index].firstName
        lastnameTextView.text = employees[index].lastName
        jobtitleTextView.text = employees[index].jobTitle
        employeeInfoTextView.text = getString(R.string.basic_text)

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
        val text = (view as TextView).text.toString()
        showEmployeeData(text.toInt() - 1)
    }
}

