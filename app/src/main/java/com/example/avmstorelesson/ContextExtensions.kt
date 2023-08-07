package com.example.avmstorelesson

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


fun Context.showToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun Context.showAlert(title:String, message:String){
    AlertDialog.Builder(this).setTitle(title).setMessage(message).create().show()
}