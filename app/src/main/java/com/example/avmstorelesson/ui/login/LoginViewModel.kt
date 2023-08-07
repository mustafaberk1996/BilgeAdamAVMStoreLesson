package com.example.avmstorelesson.ui.login

import androidx.lifecycle.ViewModel
import com.example.avmstorelesson.Database
import com.example.avmstorelesson.data.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel:ViewModel() {


    private val _loginState:MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val loginState:StateFlow<LoginState> = _loginState

    fun login(email:String, password:String){
        _loginState.value = LoginState.Loading
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){

            Database.users.firstOrNull { it.email == email && it.password == password }?.let {
                //success
                _loginState.value = LoginState.Login
            }?: kotlin.run {
                //error, user not found
                _loginState.value = LoginState.Error("user not found")
            }
        }else{
            //uyari
            _loginState.value = LoginState.Error("do not leave empty field")
        }
    }


}