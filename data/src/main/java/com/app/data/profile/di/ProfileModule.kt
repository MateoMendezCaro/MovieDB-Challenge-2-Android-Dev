package com.app.data.profile.di

import android.content.Context
import androidx.room.Room
import com.app.data.profile.ProfileRepositoryImpl
import com.app.data.profile.database.ProfileDatabase
import com.app.data.profile.local.ProfileDao
import com.app.domain.profile.ProfileRepository
import com.app.domain.profile.uc.GetProfileUC
import com.app.domain.profile.uc.SaveProfileUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileDatabase(@ApplicationContext context: Context): ProfileDatabase {
        return Room.databaseBuilder(
            context,
            ProfileDatabase::class.java,
            "profile_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProfileDao(database: ProfileDatabase): ProfileDao {
        return database.profileDao()
    }

    @Provides
    @Singleton
    fun provideProfileRepository(dao: ProfileDao): ProfileRepository {
        return ProfileRepositoryImpl(dao)
    }

    @Provides
    fun provideSaveProfileUseCase(repository: ProfileRepository): SaveProfileUC {
        return SaveProfileUC(repository)
    }

    @Provides
    fun provideGetProfileUseCase(repository: ProfileRepository): GetProfileUC {
        return GetProfileUC(repository)
    }
}