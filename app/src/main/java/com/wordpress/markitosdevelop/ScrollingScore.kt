package com.wordpress.markitosdevelop

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scrolling_score.*
import kotlinx.android.synthetic.main.activity_scrolling_score.fab
import kotlinx.android.synthetic.main.content_scrolling_score.*
import android.text.InputType
import android.widget.*
import android.widget.AdapterView
import android.view.View


class ScrollingScore : AppCompatActivity() {

    val score: Score = Score()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling_score)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.title_new_score)

        // Get the Intent that started this activity and extract the string
        //val player = intent.getStringExtra("player")
        val date = intent.getStringExtra("date")

        //EditText player
        editPlayer.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        editPlayer.setText(date)

        // Spinner Place
        spinnerMethod()



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    fun spinnerMethod() {
        val spinner: Spinner = findViewById(R.id.editPlace)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val place = parent.getItemAtPosition(position).toString().toLowerCase()
                score.wonder = place.capitalize()

                //Setea la imagen dependiendo del nombre de lugar que venga
                expandedImage.setImageResource(
                    resources.getIdentifier(
                        place,
                        "drawable",
                        packageName
                    )
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

    }

}

