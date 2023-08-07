package com.example.avmstorelesson.data.state

sealed class StoreAddState {
    object Idle:StoreAddState()
    class Added(val index:Int):StoreAddState()
}