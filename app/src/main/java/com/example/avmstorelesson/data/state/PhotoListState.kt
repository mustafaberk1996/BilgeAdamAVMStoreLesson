package com.example.avmstorelesson.data.state

import com.example.avmstorelesson.data.model.Photo

sealed class PhotoListState{
    object Idle:PhotoListState()
    class Result(val photos:List<Photo>):PhotoListState()
}
