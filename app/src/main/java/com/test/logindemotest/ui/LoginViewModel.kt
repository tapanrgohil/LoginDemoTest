package com.test.logindemotest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.logindemotest.data.LoginRequest
import com.test.logindemotest.data.authentication.AuthenticationRepository
import com.test.logindemotest.data.core.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _loginLiveData = MutableLiveData<Resource<String>>()
    val loginLiveData = _loginLiveData as LiveData<Resource<String>>

    fun login(userId: String, password: String) {
        authenticationRepository.login(LoginRequest(userId, password))
            .launchInBackGround(this, _loginLiveData)
    }
}