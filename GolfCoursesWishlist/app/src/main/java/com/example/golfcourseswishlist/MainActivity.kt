package com.example.golfcourseswishlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isListView = true
    private lateinit var mStaggeredLayoutManager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Use StaggeredGridLayoutManager as a layout manager for recyclerView
        mStaggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = mStaggeredLayoutManager
        // Use GolfCourseWishlistAdapter as a adapter for recyclerView
        recyclerView.adapter = GolfCourseWishlistAdapter(Places.placeList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_toggle -> {
                if(isListView) {
                    item.setIcon(R.drawable.ic_view_stream_white_24dp)
                    item.title = "Show as list"
                    isListView = false
                    mStaggeredLayoutManager.spanCount = 2
                } else {
                    item.setIcon(R.drawable.ic_view_column_white_24dp)
                    item.title = "Show as grid"
                    isListView = true
                    mStaggeredLayoutManager.spanCount = 1
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
