package com.app.data.people.local

import com.app.data.base.database.UpdateStorageOperation
import com.app.data.people.model.PeopleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PeopleLocalSourceImpl @Inject constructor(
    private val peopleDao: PeopleDao
) : PeopleLocalSource {

    override fun getAll(): Flow<List<PeopleEntity>> {
        return peopleDao.getAll().flowOn(Dispatchers.IO)
    }

    override fun getById(id: String): Flow<PeopleEntity?> {
        return peopleDao.getByIdFlow(id).flowOn(Dispatchers.IO)
    }

    override fun createOrUpdate(items: List<PeopleEntity>): List<PeopleEntity> {
        return object : UpdateStorageOperation<PeopleEntity>() {
            override fun getAllIds(): List<String>? = peopleDao.getAllId()

            override fun createOrUpdate(item: PeopleEntity) = peopleDao.insert(items)

            override fun delete(id: String) {
                peopleDao.delete(id)
            }
        }.execute(items)
    }

    override fun createOrUpdate(items: PeopleEntity): PeopleEntity {
        if (peopleDao.update(items) == 0) {
            peopleDao.insert(listOf(items))
        }
        return items
    }

    override fun deleteAll(): Int {
        return peopleDao.deleteAll()
    }

    override fun deleteById(id: String): Int = peopleDao.delete(id)

    override fun loadById(id: String): PeopleEntity? {
        return peopleDao.loadById(id)
    }
}