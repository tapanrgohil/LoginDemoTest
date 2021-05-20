package com.test.logindemotest.data.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


//This is my sample from boiler-plate code thos function can be large to handle offline data as well
fun <Api, Res> getFLow(api: suspend () -> Api, mapper: (Api) -> Res): Flow<Resource<Res>> {
    return flow {
        emit(Resource.loading())
        try {
            val result = api.invoke()
            val data = mapper.invoke(result)
            emit(Resource.success(data))
        } catch (e: Exception) {
            emit(Resource.error<Res>(e.localizedMessage, throwable = e))
        }
    }
}