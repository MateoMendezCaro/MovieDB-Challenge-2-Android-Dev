package com.app.data.base.database

import android.app.Application
import androidx.room.Room
import com.app.data.movies.local.MovieDao
import com.app.data.people.local.PeopleDao
import com.app.data.series.local.SeriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RoomDao {
        return Room.databaseBuilder(
            app,
            RoomDao::class.java,
            "RoomDB"
        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    fun provideMovieDao(database: RoomDao): MovieDao {
        return database.movieDao()
    }

    @Provides
    fun provideSeriesDao(database: RoomDao): SeriesDao {
        return database.seriesDao()
    }
    @Provides
    fun providePeopleDao(database: RoomDao): PeopleDao {
        return database.peopleDao()
    }
}