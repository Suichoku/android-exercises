package com.example.employessapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_employee.*
import org.json.JSONObject

class EmployeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        // get data from intent
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val employeeString = bundle.getString("employee")
            val employee = JSONObject(employeeString)
            nameTextView.text = getString(R.string.nameString, employee["firstName"].toString(), employee["lastName"].toString())
            titleTextView.text = getString(R.string.titleString, employee["title"].toString())
            emailTextView.text = getString(R.string.emailString, employee["email"].toString())
            phoneTextView.text = getString(R.string.phoneString, employee["phone"].toString())
            departmentTextView.text = getString(R.string.departmentString, employee["department"].toString())
            Glide.with(this).load(employee["image"].toString()).into(avatarImageView)
        }
    }
}
