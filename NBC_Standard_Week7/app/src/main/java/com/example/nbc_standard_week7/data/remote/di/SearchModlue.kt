package com.example.nbc_standard_week7.data.remote.di

import com.example.nbc_standard_week7.data.repository.FoodItemRepositoryImpl
import com.example.nbc_standard_week7.presentation.repository.FoodItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SearchModlue  {
    @Binds // 생성자를 가질 수 없는 인터페이스에 종속성 삽입
    fun bindsFoodRepository(foodItemRepositoryImpl: FoodItemRepositoryImpl) : FoodItemRepository
}