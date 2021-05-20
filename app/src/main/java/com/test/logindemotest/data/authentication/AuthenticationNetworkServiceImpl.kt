package com.test.logindemotest.data.authentication

import com.test.logindemotest.data.LoginResponse
import kotlinx.coroutines.delay

//Simulates network/retrofit interface
class AuthenticationNetworkServiceImpl : AuthenticationNetworkService {

    override suspend fun login(userId: String, password: String): LoginResponse {
        delay(2000) //simulates network latency
        return checkValidCredential(userId, password)
    }
        //Generally this executes on server side not on front end
    private fun checkValidCredential(userId: String, password: String): LoginResponse {
        if (userId == "123456" && password == "password") {
            return LoginResponse("Success", "123456")
        } else if (userId != "123456") {
            throw RuntimeException("UserId does not found in record")
        } else {
            throw RuntimeException("UserId and password does not match (Check password and try again)")
        }
    }
}