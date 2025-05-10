package com.app.data.people.local

import androidx.room.Dao
import androidx.room.Query
import com.app.data.base.database.BaseDao
import com.app.data.people.model.PeopleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao : BaseDao<PeopleEntity> {

    @Query("SELECT * FROM PeopleEntity")
    fun getAll(): Flow<List<PeopleEntity>>

    @Query("SELECT id FROM PeopleEntity")
    fun getAllId(): List<String>?

    @Query("SELECT * FROM PeopleEntity WHERE id = :id")
    fun getByIdFlow(id: String): Flow<PeopleEntity>

    @Query("DELETE FROM PeopleEntity")
    fun deleteAll(): Int

    @Query("DELETE FROM PeopleEntity WHERE id = :id")
    fun delete(id: String): Int

    @Query("SELECT * FROM PeopleEntity WHERE id = :id")
    fun loadById(id: String): PeopleEntity?
}