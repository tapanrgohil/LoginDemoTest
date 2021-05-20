package com.test.logindemotest.data.authentication

import com.test.logindemotest.data.LoginRequest
import com.test.logindemotest.data.core.Resource
import com.test.logindemotest.data.core.getFLow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.contracts.ExperimentalContracts

interface AuthenticationRepository {

    fun login(loginRequest: LoginRequest): Flow<Resource<String>>
}

@ExperimentalContracts
@Singleton
class AuthenticationRepositoryImpl @Inject constructor(private val networkService: AuthenticationNetworkService) :
    AuthenticationRepository {

    override fun login(loginRequest: LoginRequest): Flow<Resource<String>> {
        return getFLow(api = {
            networkService.login(loginRequest.userId, loginRequest.password)
        }, mapper = {
            it.userId //can be mapped to requiredData
        })
    }


}

