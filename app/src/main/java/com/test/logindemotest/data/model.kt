package com.test.logindemotest.data


data class LoginRequest(val userId: String, val password: String)
data class LoginResponse(val message: String, val userId: String)