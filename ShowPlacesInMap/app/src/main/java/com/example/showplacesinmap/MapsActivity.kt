package com.example.showplacesinmap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
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

        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.myjson.com/bins/z78wv.json"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val places = response.getJSONArray("places")
                showMarkers(places)
            },
            Response.ErrorListener { error ->
                Log.d("JSON", error.toString())
            })

        queue.add(jsonObjectRequest)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        val vaasa = LatLng(63.1055261, 21.5932938)

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vaasa, 15.5F))
        mMap.setOnMarkerClickListener {
            Toast.makeText(this, it.title, Toast.LENGTH_LONG).show()
            true
        }
    }

    private fun showMarkers(places: JSONArray) {

        for(i in 0 until places.length()) {
            val place = places.getJSONObject(i)
            val lat = place["latitude"].toString().toDouble()
            val lng = place["longitude"].toString().toDouble()
            mMap.addMarker(MarkerOptions()
                .position(LatLng(lat,lng))
                .title(place["name"].toString())
            )
        }
    }
}
