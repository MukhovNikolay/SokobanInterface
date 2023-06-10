package com.example.sokobaninterface

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams;
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Paths


class RecordActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.records)
        val table = findViewById<TableLayout>(R.id.tableRecords)
        val exit = findViewById<Button>(R.id.exitMenuRecord)
        val drop = findViewById<Button>(R.id.drop)
        exit.setOnClickListener {
            val intent = Intent(this@RecordActivity, MainActivity::class.java)
            startActivity(intent)
        }
        drop.setOnClickListener{
            val fOut: FileOutputStream = openFileOutput("records.txt", MODE_PRIVATE)
            val osw = OutputStreamWriter(fOut)
            osw.write("")
        }
        val path = Paths.get(applicationContext.filesDir.toString() + "/" + "records.txt")
        var records = arrayOf<Array<String>>()
        Files.lines(path, Charsets.UTF_8).forEach {
            var record = arrayOf<String>()
            var str = ""
            for (col in it){
                if(col==','){
                    record += str
                    str = ""
                }else{
                    str += col.toString()
                }
            }
            record += str
            records += record
        }
        for (row in records){
            val tableRow = TableRow(this)
            val level_name = TextView(this)
            val moves = TextView(this)
            val time = TextView(this)
            level_name.text = row[0]
            moves.text = row[1]
            time.text = String.format("%02d:%02d", row[2].toInt()/60, row[2].toInt()%60)
            level_name.gravity = Gravity.CENTER
            moves.gravity = Gravity.CENTER
            time.gravity = Gravity.CENTER
            tableRow.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            tableRow.addView(level_name,0)
            tableRow.addView(moves,1)
            tableRow.addView(time,2)
            table.addView(tableRow)
        }
    }
}