package com.example.sokobaninterface

import android.util.Log

class Game(init_level:Array<Array<Int>>) {
    var char_pos_col = 0
    var char_pos_row = 0
    var move_path = "N"
    var level = init_level.clone()

    fun initPos() {
        for (row in level.indices){
            for (col in 0 until level[0].size){
                if(level[row][col] == 4){
                    char_pos_row = row
                    char_pos_col = col
                    Log.i("Char", "Character found $col $row")
                }
            }
        }
    }
    fun move(direction:String): Boolean {
//        var goal_char:Boolean = false
//        if(level[char_pos_row][char_pos_col] == 7){
//            goal_char = true
//        }
        if (canMove(direction)) {
//            if(goal_char){
//                level[char_pos_row][char_pos_col] = 5
//            }
            var old_pos_row = char_pos_row
            var old_pos_col = char_pos_col
            when (direction) {
                "U"-> char_pos_row -=1
                "D"-> char_pos_row +=1
                "L"-> char_pos_col -=1
                "R"-> char_pos_col +=1
            }
            if((level[char_pos_row][char_pos_col] == 3)||(level[char_pos_row][char_pos_col] == 6)) {
                moveBox(old_pos_row, old_pos_col)
                move_path += direction.lowercase()
            }
            else{
                move_path += direction
            }

            if(level[char_pos_row][char_pos_col]==5){
                level[char_pos_row][char_pos_col] = 7
            }
            else{
                level[char_pos_row][char_pos_col] = 4
            }

            if(level[old_pos_row][old_pos_col] == 4){
                level[old_pos_row][old_pos_col] = 2
            }
            else{
                level[old_pos_row][old_pos_col] = 5
            }

            Log.i("Move","move success$char_pos_row $char_pos_col")
            return true
        }else{
            return false
        }
    }
    private fun canMove(direction: String): Boolean {
        var step1 = 0
        var step2 = 0
        when(direction){
            "U"->{
                step1 = level[char_pos_row-1][char_pos_col]
                step2 = level[char_pos_row-2][char_pos_col]
            }
            "D"->{
                step1 = level[char_pos_row+1][char_pos_col]
                step2 = level[char_pos_row+2][char_pos_col]
            }
            "L"->{
                step1 = level[char_pos_row][char_pos_col-1]
                step2 = level[char_pos_row][char_pos_col-2]
            }
            "R"->{
                step1 = level[char_pos_row][char_pos_col+1]
                step2 = level[char_pos_row][char_pos_col+2]
            }
        }
        Log.i("canMove","step1 $step1, step2 $step2")
        return !((((step1 == 3)||(step1 == 6))&&((step2 == 1)||(step2 == 3)||(step2 == 6)))||(step1 == 1))
    }
    private fun moveBox(old_pos_row: Int,old_pos_col: Int){
        val new_box_row = 2*char_pos_row - old_pos_row
        val new_box_col = 2*char_pos_col - old_pos_col
        if(level[new_box_row][new_box_col]==5){
            level[new_box_row][new_box_col] = 6
        }
        else{
            level[new_box_row][new_box_col] = 3
        }
        if(level[char_pos_row][char_pos_col] == 6){
            level[char_pos_row][char_pos_col] = 5
        }
        else{
            level[char_pos_row][char_pos_col] = 2
        }
    }
    fun backMove(direction: Char){
        when (direction) {
            'U' -> {
                if (level[char_pos_row][char_pos_col] == 4) {
                    level[char_pos_row][char_pos_col] = 2
                } else {
                    level[char_pos_row][char_pos_col] = 5
                }
                char_pos_row += 1
            }
            'u' -> {
                if (level[char_pos_row - 1][char_pos_col] == 3) {
                    level[char_pos_row - 1][char_pos_col] = 2
                } else {
                    level[char_pos_row - 1][char_pos_col] = 5
                }
                if (level[char_pos_row][char_pos_col] == 4) {
                    level[char_pos_row][char_pos_col] = 3
                } else {
                    level[char_pos_row][char_pos_col] = 6
                }
                char_pos_row += 1
            }
            'D' -> {
                if (level[char_pos_row][char_pos_col] == 4) {
                    level[char_pos_row][char_pos_col] = 2
                } else {
                    level[char_pos_row][char_pos_col] = 5
                }
                char_pos_row -= 1
            }
            'd' -> {
                if (level[char_pos_row + 1][char_pos_col] == 3) {
                    level[char_pos_row + 1][char_pos_col] = 2
                } else {
                    level[char_pos_row + 1][char_pos_col] = 5
                }
                if (level[char_pos_row][char_pos_col] == 4) {
                    level[char_pos_row][char_pos_col] = 3
                } else {
                    level[char_pos_row][char_pos_col] = 6
                }
                char_pos_row -= 1
            }
            'L' -> {
                if (level[char_pos_row][char_pos_col] == 4) {
                    level[char_pos_row][char_pos_col] = 2
                } else {
                    level[char_pos_row][char_pos_col] = 5
                }
                char_pos_col += 1
            }
            'l' -> {
                if (level[char_pos_row][char_pos_col - 1] == 3) {
                    level[char_pos_row][char_pos_col - 1] = 2
                } else {
                    level[char_pos_row][char_pos_col - 1] = 5
                }
                if (level[char_pos_row][char_pos_col] == 4) {
                    level[char_pos_row][char_pos_col] = 3
                } else {
                    level[char_pos_row][char_pos_col] = 6
                }
                char_pos_col += 1
            }
            'R' -> {
                if (level[char_pos_row][char_pos_col] == 4) {
                    level[char_pos_row][char_pos_col] = 2
                } else {
                    level[char_pos_row][char_pos_col] = 5
                }
                char_pos_col -= 1
            }
            'r' -> {
                if (level[char_pos_row][char_pos_col + 1] == 3) {
                    level[char_pos_row][char_pos_col + 1] = 2
                } else {
                    level[char_pos_row][char_pos_col + 1] = 5
                }
                if (level[char_pos_row][char_pos_col] == 4) {
                    level[char_pos_row][char_pos_col] = 3
                } else {
                    level[char_pos_row][char_pos_col] = 6
                }
                char_pos_col -= 1
            }
        }
        if (level[char_pos_row][char_pos_col] == 2) {
            level[char_pos_row][char_pos_col] = 4
        } else {
            level[char_pos_row][char_pos_col] = 7
        }
        move_path = move_path.dropLast(1)
    }
    fun isWon(): Boolean {
        for (row in level.indices){
            for (col in 0 until level[0].size) {
                if((level[row][col] == 5)||(level[row][col] == 7)){
                    return false
                }
            }
        }
        Log.i("Win","Win path: $move_path")
        return true
    }
}

