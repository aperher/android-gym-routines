package com.example.gymroutines.di

import com.example.gymroutines.data.profile.ProfileDataSource
import com.example.gymroutines.data.profile.ProfileDataSourceImpl
import com.example.gymroutines.data.profile.ProfileRepository
import com.example.gymroutines.data.profile.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProfileBinderModule {
    @Binds
    abstract fun bindProfileRepository(profileRepository: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun bindProfileDataSource(profileDataSource: ProfileDataSourceImpl): ProfileDataSource
}