package com.app.data.base.mapper

import com.app.data.movies.model.MovieEntity

interface MapperDomain<Model, Entity> {

    fun mapRemote(entity: Entity): Model

    fun mapLocal(model: Model): Entity {
        return TODO() // implement when required
    }
}