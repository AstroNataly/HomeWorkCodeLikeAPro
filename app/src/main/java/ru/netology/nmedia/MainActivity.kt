package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("NMEDIA!")
        println(R.string.nmedia)
        println(getString(R.string.nmedia))
    }
}