package com.example.avmstorelesson.data.state

sealed class LoginState{
    object Idle:LoginState()
    object Loading:LoginState()
    object Login:LoginState()
    class Error(val message:String):LoginState()
}
