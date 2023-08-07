package com.example.avmstorelesson.data.state

import com.example.avmstorelesson.data.model.Store

sealed class StoreDeleteState {
    object Idle:StoreDeleteState()
    class Success(val deletedStore:Store, val index:Int):StoreDeleteState()
    object Failed:StoreDeleteState()

}