package com.example.gymroutines.data.di

import com.example.gymroutines.data.auth.AuthDataSource
import com.example.gymroutines.data.auth.AuthDataSourceImpl
import com.example.gymroutines.data.auth.AuthRepository
import com.example.gymroutines.data.auth.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthBinderModule {
    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindAuthDataSource(authDataSource: AuthDataSourceImpl): AuthDataSource
}