package com.hoahieu.demo.data.model.mapper

interface DataMapper<DataModel, DomainModel> {
    fun mapFromEntity(entity: DataModel): DomainModel
}