package com.example.avmstorelesson.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.avmstorelesson.data.state.LoginState
import com.example.avmstorelesson.databinding.ActivityLoginBinding
import com.example.avmstorelesson.showToast
import com.example.avmstorelesson.ui.main.MainActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        observeLoginState()


        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }
    }

    private fun observeLoginState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.loginState.collect{
                    drawLoginState(it)
                }
            }
        }
    }

    private fun drawLoginState(loginState: LoginState) {
        when(loginState){
            is LoginState.Idle->{}
            is LoginState.Loading->{
                binding.progressBar.isVisible = true
                binding.btnLogin.isVisible = false
            }
            is LoginState.Login->{
                binding.progressBar.isVisible = false
                binding.btnLogin.isVisible = true
                startActivity(
                    Intent(this, MainActivity::class.java)
                )
            }
            is LoginState.Error->{
                binding.progressBar.isVisible = false
                binding.btnLogin.isVisible = true
                showToast(loginState.message)
            }
        }
    }
}