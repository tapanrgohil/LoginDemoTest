package com.test.logindemotest.data.authentication

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.contracts.ExperimentalContracts

@Module(includes = [AuthenticationSources::class])
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationApiService(): AuthenticationNetworkService {
        return AuthenticationNetworkServiceImpl()
    }


}


@Module()
@InstallIn(SingletonComponent::class)
abstract class AuthenticationSources {
    @ExperimentalContracts
    @Binds
    @Singleton
    abstract fun provideAuthenticationRepo(impl: AuthenticationRepositoryImpl): AuthenticationRepository

}
