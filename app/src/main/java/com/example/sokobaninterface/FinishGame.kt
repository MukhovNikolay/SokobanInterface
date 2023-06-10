package com.example.sokobaninterface

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment

class FinishGame():DialogFragment() {
override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it)
                .setMessage("Уровень пройден")
                .setPositiveButton(
                    "Вернуться в меню",
                    activity as DialogInterface.OnClickListener
                )
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}