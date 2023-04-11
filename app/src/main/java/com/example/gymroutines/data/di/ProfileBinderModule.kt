package com.example.gymroutines.data.di

import com.example.gymroutines.data.profile.ProfileDataSource
import com.example.gymroutines.data.profile.ProfileDataSourceImpl
import com.example.gymroutines.data.profile.ProfileRepository
import com.example.gymroutines.data.profile.ProfilerepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProfileBinderModule {
    @Binds
    abstract fun bindProfileRepository(profileRepository: ProfilerepositoryImpl): ProfileRepository

    @Binds
    abstract fun bindProfileDataSource(profileDataSource: ProfileDataSourceImpl): ProfileDataSource
}