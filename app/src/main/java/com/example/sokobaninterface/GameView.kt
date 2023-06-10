package com.example.sokobaninterface

import android.R.attr.*
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.RectF
import android.view.View
import java.lang.Float.min


//private var firstTime = true
//private val gameRunning = true
//private val char: com.example.sokobaninterface.Character? = null
//private val gameThread: Thread? = null
////    private val paint: Paint? = null
////    private val canvas: Canvas? = null
//private val surfaceHolder: SurfaceHolder? = null
//
//var maxX = 20 // размер экрана по горизонтали
//var maxY = 28 // размер экрана по вертикали
//
//var unitW = 0f

//var unitH = 0f

// 0 - Пустое пространство за границами игрового поля
// 1 - Стена
// 2 - Пол
// 3 - Ящик
// 4 - Персонаж
// 5 - Цель для ящика
// 6 - Ящик на ячейке цели
// 7 - Персонаж на ячейке цели

class GameView(context:Context,init_game: Game):View(context) {
    private var size = 50
    private var char = BitmapFactory.decodeResource(resources, R.drawable.character)
    private var box = BitmapFactory.decodeResource(resources, R.drawable.box)
    private var floor = BitmapFactory.decodeResource(resources, R.drawable.floor)
    private var wall = BitmapFactory.decodeResource(resources, R.drawable.wall)
    private var void = BitmapFactory.decodeResource(resources, R.drawable.void_game)
    private var goal = BitmapFactory.decodeResource(resources, R.drawable.goal)
    private var goal_box = BitmapFactory.decodeResource(resources, R.drawable.goal_box)
    private var goal_char = BitmapFactory.decodeResource(resources, R.drawable.goal_char)
    private val rect: RectF = RectF()
//    private var level = game.level
    private val game = init_game
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = canvas.width
        val h = canvas.height
        val level = game.level
        size = min((w/level[0].size).toFloat(), (h/level.size).toFloat()).toInt()
        for (row in level.indices) {
            for (col in 0 until level[0].size) {
                var pict: Bitmap? = null
                when (level[row][col]) {
                    0 -> pict = void
                    1 -> pict = wall
                    2 -> pict = floor
                    3 -> pict = box
                    4 -> pict = char
                    5 -> pict = goal
                    6 -> pict = goal_box
                    7 -> pict = goal_char
                }
                if (pict != null) {
                    rect.set(
                        (size * col).toFloat(), (size * row).toFloat(),
                        (size * (col + 1)).toFloat(), (size * (row + 1)).toFloat()
                    )
                    canvas.drawBitmap(pict, null, rect, null);
                }

            }
        }
//        Log.i("canvas","canvas called")
    }
//    override fun invalidate() {
//    }
}


//    override fun onCl{
//        up.setOnClickListener {
//            game.move("up")
//        }
//        down.setOnClickListener {
//            game.move("down")
//        }
//        right.setOnClickListener {
//            game.move("right")
//            Log.i("Move","Move success")
//        }
//        left.setOnClickListener {
//            game.move("left")
//        }
//    }



//    fun move(direction:String): String {
//        if(direction == "up"){
//            return "Up"
//        }
//        if(direction == "down"){
//            return "Down"
//        }
//        if(direction == "left"){
//            return "Left"
//        }
//        if(direction == "right"){
//            return "Right"
//        }
//        return "None"
//    }


//private fun draw() {
//    if (surfaceHolder?.getSurface()?.isValid() == true) {  //проверяем валидный ли surface
//        if (firstTime) { // инициализация при первом запуске
//            firstTime = false
//            unitW =
//                surfaceHolder.getSurfaceFrame().width() / maxX // вычисляем число пикселей в юните
//            unitH = surfaceHolder.getSurfaceFrame().height() / maxY
//            char = Character(getContext()) // добавляем персонажа
//        }
//        canvas = surfaceHolder.lockCanvas() // закрываем canvas
//        canvas.drawColor(Color.BLACK) // заполняем фон чёрным
//        ship.drow(paint, canvas) // рисуем корабль
//        surfaceHolder.unlockCanvasAndPost(canvas) // открываем canvas
//    }
//}

