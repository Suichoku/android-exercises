package com.example.showgolfcoursesinmap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val queue = Volley.newRequestQueue(this)
        val url = "http://ptm.fi/materials/golfcourses/golf_courses.json"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val courses = response.getJSONArray("courses")
                showMarkers(courses)
            },
            Response.ErrorListener { error ->
                Log.d("JSON", error.toString())
            })

        queue.add(jsonObjectRequest)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        val initPos = LatLng(65.2112851, 26.2524546)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initPos, 4.7F))
        mMap.setInfoWindowAdapter(CustomInfoWindowAdapter())

    }

    private fun showMarkers(courses: JSONArray) {

        for(i in 0 until courses.length()) {
            val course = courses.getJSONObject(i)
            val lat = course["lat"].toString().toDouble()
            val lng = course["lng"].toString().toDouble()
            val color = when(course["type"]) {
                "Kulta" -> BitmapDescriptorFactory.HUE_YELLOW
                "Kulta/Etu" -> BitmapDescriptorFactory.HUE_ORANGE
                "Etu" -> BitmapDescriptorFactory.HUE_GREEN
                else -> BitmapDescriptorFactory.HUE_AZURE
            }

            mMap.addMarker(MarkerOptions()
                .position(LatLng(lat,lng))
                .title(course["course"].toString())
                .snippet("${course["address"]}\n" +
                        "${course["phone"]}\n" +
                        "${course["email"]}\n" +
                        "${course["web"]}\n")
                .icon(BitmapDescriptorFactory.defaultMarker(color))
            )
        }
    }

    // reference:
    // https://github.com/googlemaps/android-samples/blob/master/ApiDemos/kotlin/app/src/main/java/com/example/kotlindemos/MarkerDemoActivity.kt

    internal inner class CustomInfoWindowAdapter : GoogleMap.InfoWindowAdapter {

        private val contents: View = layoutInflater.inflate(R.layout.custom_info_window, null)

        override fun getInfoWindow(marker: Marker?): View? {
            return null
        }

        override fun getInfoContents(marker: Marker): View {
            render(marker, contents)
            return contents
        }

        private fun render(marker: Marker, view: View) {

            val title: String? = marker.title
            val titleUi = view.findViewById<TextView>(R.id.nameTextView)

            if (title != null) titleUi.text = title
            else titleUi.text = ""

            val snippet: String? = marker.snippet
            val snippetUi = view.findViewById<TextView>(R.id.snippetTextView)
            if (snippet != null) snippetUi.text = snippet
            else snippetUi.text = ""
        }

    }

}
