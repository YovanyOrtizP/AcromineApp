package com.example.acromineapp.di

import com.example.acromineapp.data.repository.AcromineRepository
import com.example.acromineapp.data.repository.AcromineRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
//to the view model, because you are gonna need it only there
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    //Difference between bind y provides
    //provides you need to create the instance of the object that you are providing
    //Binds it's only try to get all the dependencies and provides that interface that you need
    //binds is only for interfaces
    //interface is the abstraction of the implementation, that's why we need abstract class

    @Binds
    abstract fun provideRepository(acromineRepository: AcromineRepositoryImpl): AcromineRepository
}