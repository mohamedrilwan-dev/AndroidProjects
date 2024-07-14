package com.example.university_programs

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var screenSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val screens = arrayOf("-- Choose --", "Undergraduate", "Graduate", "Diploma/Certificate")
        val button: Button = findViewById(R.id.btn1)
        val button2: ImageButton = findViewById(R.id.imageBtn)
        screenSpinner = findViewById(R.id.screenSpinner)
        var selectedView : Fragment = WelcomeFragment()

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, screens)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        screenSpinner.adapter = adapter

        screenSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View,
                position: Int,
                id: Long
            ) {
                selectedView = when (position) {
                    1 -> UndergraduateFragment()
                    2 -> GraduateFragement()
                    3 -> DiplomaFragment()
                    else -> WelcomeFragment()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing here
            }

        }

        button.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frameView, selectedView)
                    .commit()
            }

        button2.setOnClickListener {
            selectedView= WelcomeFragment();
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameView, selectedView)
                .commit()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameView, WelcomeFragment())
                .commit()
        }
    }
}