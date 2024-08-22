package com.noxis.unittest.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.noxis.unittest.data.local.ShoppingItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        ShoppingItemDatabase::class.java
    ).allowMainThreadQueries().build()
}