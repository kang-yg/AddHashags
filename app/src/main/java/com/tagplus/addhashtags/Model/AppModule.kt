package com.tagplus.addhashtags.Model

import android.content.Context
import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseDatabaseReference(): DatabaseReference = Firebase.database.reference

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "AddHashTags")
            .addMigrations(MIGRATION_1_2)
            .build()

    @Singleton
    @Provides
    fun provideLocalServiceHelper(localServiceHelperImpl: LocalServiceHelperImpl): LocalServiceHelper = localServiceHelperImpl
}