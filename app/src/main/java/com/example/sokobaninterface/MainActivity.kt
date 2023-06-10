package com.example.sokobaninterface

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Paths

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val mediaPlay = MediaPlayer.create(this,R.raw.may_it_be)
        val play = findViewById<Button>(R.id.play)
        val settings = findViewById<Button>(R.id.settings)
        val records = findViewById<Button>(R.id.records)
//        val file = findViewById<ImageView>(R.id.imageView)
        play.setOnClickListener{
            val intent = Intent(this@MainActivity, ChoiceActivity::class.java)
//            mediaPlay.start()
//            println(1)
            startActivity(intent)
        }
        settings.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(intent)
        }
        records.setOnClickListener {
            val intent = Intent(this@MainActivity, RecordActivity::class.java)
            startActivity(intent)
        }
    }
}