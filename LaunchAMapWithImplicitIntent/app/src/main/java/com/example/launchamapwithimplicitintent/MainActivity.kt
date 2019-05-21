package com.example.launchamapwithimplicitintent

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showMap(view: View?) {

        val lat: String = latEditText.text.toString() // get given latitude
        val lng: String = lngEditText.text.toString() // get given longitude

        val pattern = """\d{1,3}\.\d*""".toRegex() // regex pattern that given values should match
        if(pattern.matches(lat) && pattern.matches(lng)) { // check if given values match regex pattern
            // check if values are in valid range
            if(lat.toDouble() <= 90 && lat.toDouble() >= -90 && lng.toDouble() <= 180 && lng.toDouble() >= -180) {
                val location = Uri.parse("geo:$lat,$lng")
                val mapIntent = Intent(Intent.ACTION_VIEW, location)

                val isIntentSafe: Boolean = packageManager
                    .queryIntentActivities(mapIntent,0).isNotEmpty()

                if(isIntentSafe) {
                    startActivity(mapIntent)
                } else {
                    Toast.makeText(this, "There is no activity to handle map intent!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Given values are out of valid range", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Please give valid latitude and longitude values", Toast.LENGTH_LONG).show()
        }

    }
}
