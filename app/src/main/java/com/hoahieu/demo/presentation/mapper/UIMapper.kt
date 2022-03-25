package com.hoahieu.demo.presentation.mapper

interface UIMapper<DomainModel, UIModel> {
    fun mapFromDomain(model: DomainModel): UIModel
}