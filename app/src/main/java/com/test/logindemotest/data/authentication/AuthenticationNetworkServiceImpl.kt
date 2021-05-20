package com.test.logindemotest.data.authentication

import com.test.logindemotest.data.LoginResponse
import kotlinx.coroutines.delay

//Simulates network/retrofit interface
class AuthenticationNetworkServiceImpl : AuthenticationNetworkService {

    override suspend fun login(userId: String, password: String): LoginResponse {
        delay(2000) //simulates network latency
        return checkValidCredential(userId, password)
    }

    private fun checkValidCredential(userId: String, password: String): LoginResponse {
        if (userId == "123456" && password == "password") {
            return LoginResponse("Success", "123456")
        } else {
            throw RuntimeException("Invalid Credentials")
        }
    }
}