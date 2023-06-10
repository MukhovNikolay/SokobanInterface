package com.example.sokobaninterface

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.concurrent.timer

class GameActivity : AppCompatActivity(),DialogInterface.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val level_text = intent.getStringExtra("level")
        val level = levelLoad( level_text + ".txt")

        setContentView(R.layout.game)
        val up = findViewById<ImageButton>(R.id.up_arrow)
        val down = findViewById<ImageButton>(R.id.down_arrow)
        val left = findViewById<ImageButton>(R.id.left_arrow)
        val right = findViewById<ImageButton>(R.id.right_arrow)
        val back = findViewById<ImageButton>(R.id.back)
        val restart = findViewById<ImageButton>(R.id.restart)
        val pause = findViewById<ImageButton>(R.id.pause)
        val moves = findViewById<TextView>(R.id.moves_count)
        val level_name = findViewById<TextView>(R.id.level_name)
        val time = findViewById<TextView>(R.id.time_count)

        val gameViewLayout = findViewById<FrameLayout>(R.id.gameViewPlace)

        val game = Game(level)
        level_name.text = level_text
        moves.text = "0"
        time.text = "00:00"
        game.initPos()
        val gameView = GameView(this,game)
        gameViewLayout.addView(gameView)

        var timer = 0
        var pause_check = true
        Thread{
            while (true) {
                Thread.sleep(1000)
                if(pause_check) {
                    timer++
                    runOnUiThread {time.text = String.format("%02d:%02d", timer/60, timer%60) }
                }
            }
        }.start()

        up.setOnClickListener {
            if(pause_check) {
                if (game.move("U")) {
                    moves.text = (moves.text.toString().toInt() + 1).toString()
                }
                gameView.invalidate()
                if (game.isWon()) {
                    pause_check = !pause_check
                    loadRecord(level_name.text.toString(),game.move_path,moves.text.toString().toInt(),timer)
                    FinishGame().show(supportFragmentManager, "winning dialog")
                }
            }
        }
        down.setOnClickListener {
            if(pause_check) {
                if (game.move("D")) {
                    moves.text = (moves.text.toString().toInt() + 1).toString()
                }
                gameView.invalidate()
                if (game.isWon()) {
                    pause_check = !pause_check
                    loadRecord(level_name.text.toString(),game.move_path,moves.text.toString().toInt(),timer)
                    FinishGame().show(supportFragmentManager, "winning dialog")
                }
            }
        }
        right.setOnClickListener {
            if(pause_check) {
                if (game.move("R")) {
                    moves.text = (moves.text.toString().toInt() + 1).toString()
                }
                gameView.invalidate()
                if (game.isWon()) {
                    pause_check = !pause_check
                    loadRecord(level_name.text.toString(),game.move_path,moves.text.toString().toInt(),timer)
                    FinishGame().show(supportFragmentManager, "winning dialog")
                }
            }
//            Log.i("Move","Move success")
        }
        left.setOnClickListener {
            if(pause_check) {
                if (game.move("L")) {
                    moves.text = (moves.text.toString().toInt() + 1).toString()
                }
                gameView.invalidate()
                if (game.isWon()) {
                    pause_check = !pause_check
                    loadRecord(level_name.text.toString(),game.move_path,moves.text.toString().toInt(),timer)
                    FinishGame().show(supportFragmentManager, "winning dialog")
                }
            }
        }
        back.setOnClickListener {
            if(pause_check) {
                if (game.move_path != "N") {
                    moves.text = (moves.text.toString().toInt() - 1).toString()
                    game.backMove(game.move_path[game.move_path.length - 1])
                    gameView.invalidate()
                }
            }
        }
        restart.setOnClickListener{
            timer = 0
            time.text = String.format("%02d:%02d", timer/60, timer%60)
            game.level = levelLoad(level_text + ".txt")
            game.move_path = "N"
            moves.text = "0"
            game.initPos()
            gameView.invalidate()
            pause_check = true
        }
        pause.setOnClickListener {
            pause_check = !pause_check
        }
    }

    fun levelLoad(filename:String): Array<Array<Int>> {
        var grid = arrayOf<Array<Int>>()
        val path = Paths.get(applicationContext.filesDir.toString() + "/" + filename)
        Files.lines(path, Charsets.UTF_8).forEach {
            var grid_row = arrayOf<Int>()
            for (col in it){
                grid_row += col.toString().toInt()
            }
            grid += grid_row
        }
        return grid
    }
    override fun onClick(dialog: DialogInterface?, which: Int) {
        val intent = Intent(this@GameActivity, MainActivity::class.java)
        when(which){
            DialogInterface.BUTTON_POSITIVE -> startActivity(intent)
        }
    }
    fun loadRecord(level_name:String,move_path:String,moves:Int,timer:Int){

        val path = Paths.get(applicationContext.filesDir.toString() + "/" + "records.txt")
        var records = arrayOf<Array<String>>()
        val file = File(applicationContext.filesDir.toString() + "/" + "records.txt")
        if(file.exists()){
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
        }else{
            val fOut: FileOutputStream = openFileOutput("records.txt", MODE_PRIVATE)
            val osw = OutputStreamWriter(fOut)
            osw.write("")
            osw.close()
        }
        var count = true
        for (record in records){
            if(level_name == record[0]){
                if((moves < record[1].toInt())||((moves == record[1].toInt())&&(timer < record[2].toInt()))){
                    record[0] = level_name
                    record[1] = moves.toString()
                    record[2] = timer.toString()
                }
                count = false
            }
        }
        var fileStr = ""
        if(count){
            fileStr = "$level_name,$moves,$timer\n"
        }
        for (record in records){
            fileStr += record[0] + "," + record[1] + "," + record[2] + "\n"
        }

        val fOut: FileOutputStream = openFileOutput("records.txt", MODE_PRIVATE)
        val osw = OutputStreamWriter(fOut)
        osw.write(fileStr.dropLast(1))
        osw.flush()
        osw.close()
    }
}