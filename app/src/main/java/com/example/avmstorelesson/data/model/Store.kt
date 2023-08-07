package com.example.avmstorelesson.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Store (val id: Int, val name:String, val image:String, val floor:String, val block:String, val info:String): Parcelable
