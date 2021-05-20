package com.test.logindemotest.data.authentication

import com.test.logindemotest.data.LoginResponse

//Simulates network/retrofit interface
interface AuthenticationNetworkService {

    suspend fun login(userId: String, password: String): LoginResponse
}