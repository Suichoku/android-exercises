package com.example.roomwordsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter
            words?.let { adapter.setWords(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val word = Word(it.getStringExtra(NewWordActivity.EXTRA_REPLY))
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
    companion object {
        const val newWordActivityRequestCode = 1
    } */
}
