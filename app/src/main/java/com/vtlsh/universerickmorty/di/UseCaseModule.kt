package com.vtlsh.universerickmorty.di

import com.vtlsh.universerickmorty.business.ItemUseCase
import com.vtlsh.universerickmorty.data.local.ItemServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideItem(itemServiceImpl: ItemServiceImpl): ItemUseCase {
        return ItemUseCase(itemServiceImpl)
    }

}