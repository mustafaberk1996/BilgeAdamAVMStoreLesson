package com.example.avmstorelesson.data.state

sealed class AdapterState{
    object Idle:AdapterState()
    class Changed(val index:Int):AdapterState()
    class Removed(val index:Int):AdapterState()
    class Added(val index:Int):AdapterState()
}
