package com.example.sokobaninterface

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class ChoiceActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_choice)
        val exitMenu = findViewById<Button>(R.id.exitMenuChoice)
        val level1 = findViewById<Button>(R.id.level1)
        val level2 = findViewById<Button>(R.id.level2)
        val level3 = findViewById<Button>(R.id.level3)
        val level4 = findViewById<Button>(R.id.level4)
        val level5 = findViewById<Button>(R.id.level5)
        val level6 = findViewById<Button>(R.id.level6)
        val level7 = findViewById<Button>(R.id.level7)
        val level8 = findViewById<Button>(R.id.level8)
        val level9 = findViewById<Button>(R.id.level9)
        val intent = Intent(this@ChoiceActivity, GameActivity::class.java)
        level1.setOnClickListener {
            val fileString = "000000\n" + "011110\n" + "014510\n" + "013210\n" + "012210\n" + "012310\n" +
                    "015210\n" + "012210\n" + "011110\n" + "000000"
            levelLoad(fileString,"level1.txt")
            intent.putExtra("level","level1")
            startActivity(intent)
        }
        level2.setOnClickListener {
            val fileString = "0000000\n" + "0111110\n" + "0122510\n" + "0123210\n" + "0114110\n" + "0113110\n" +
                    "0122210\n" + "0152210\n" + "0111110\n" + "0000000"
            levelLoad(fileString,"level2.txt")
            intent.putExtra("level","level2")
            startActivity(intent)
        }
        level3.setOnClickListener {
            val fileString = "000000000\n" + "011111110\n" + "015212510\n" + "012343210\n" + "012212210\n" +
                    "011111110\n" + "000000000"
            levelLoad(fileString,"level3.txt")
            intent.putExtra("level","level3")
            startActivity(intent)
        }
        level4.setOnClickListener {
            val fileString = "00000000\n" + "01111110\n" + "01522210\n" + "01332210\n" + "01451110\n" +
                    "01111000\n" + "00000000"
            levelLoad(fileString,"level4.txt")
            intent.putExtra("level","level4")
            startActivity(intent)
        }
        level5.setOnClickListener {
            val fileString = "0000000000\n" + "0111111100\n" + "0155422110\n" + "0121233210\n" +
                    "0122222210\n" + "0111111110\n" + "0000000000"
            levelLoad(fileString,"level5.txt")
            intent.putExtra("level","level5")
            startActivity(intent)
        }
        level6.setOnClickListener {
            val fileString = "00000000\n" + "00111110\n" + "01122510\n" + "01432210\n" + "01226210\n" +
                    "01111110\n" + "00000000"
            levelLoad(fileString,"level6.txt")
            intent.putExtra("level","level6")
            startActivity(intent)
        }
        level7.setOnClickListener {
            val fileString = "0000000000\n" + "0000111110\n" + "0111122210\n" + "0143221210\n" +
                    "0152322510\n" + "0111111110\n" + "0000000000"
            levelLoad(fileString,"level7.txt")
            intent.putExtra("level","level7")
            startActivity(intent)
        }
        level8.setOnClickListener {
            val fileString = "0000000000\n" + "0111111110\n" + "0122512210\n" + "0122343210\n" + "0122112510\n" +
                    "0111111110\n" + "0000000000"
            levelLoad(fileString,"level8.txt")
            intent.putExtra("level","level8")
            startActivity(intent)
        }
        level9.setOnClickListener {
            val fileString = "000000000000000000000\n" + "000001111100000000000\n" + "000001222100000000000\n" +
                    "000001322100000000000\n" + "000111223110000000000\n" + "000122323210000000000\n" + "011121211210001111110\n" +
                    "012221211211111225510\n" + "012322322222222225510\n" + "011111211121211225510\n" + "000001224221111111110\n" +
                    "000001111111000000000\n" + "000000000000000000000"
            levelLoad(fileString,"level9.txt")
            intent.putExtra("level","level9")
            startActivity(intent)
        }
        exitMenu.setOnClickListener{
            val exit_intent = Intent(this@ChoiceActivity, MainActivity::class.java)
            startActivity(exit_intent)
        }

    }
    fun levelLoad(fileString:String,fileName:String){
        val fOut: FileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
        val osw = OutputStreamWriter(fOut)
        osw.write(fileString)
        osw.flush()
        osw.close()
    }
}