package com.mx.dcc.rickandmortytest.network.mappers

import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.data.CharactersResponse
import com.mx.dcc.rickandmortytest.data.OriginResponse
import com.mx.dcc.rickandmortytest.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<CharactersResponse, CharactersModel> {

    override fun mapFromEntity(entity: CharactersResponse): CharactersModel {
        return CharactersModel(
            id = entity.id,
            image = entity.image,
            name = entity.name,
            status = entity.status,
            species = entity.species,
            origin = entity.origin.name,
            urlOrigin = entity.origin.url
        )
    }

    override fun mapToEntity(domainModel: CharactersModel): CharactersResponse {
        return CharactersResponse(
            id = domainModel.id,
            image = domainModel.image,
            name = domainModel.name,
            status = domainModel.status,
            species = domainModel.species,
            origin = OriginResponse(
                domainModel.origin,
                domainModel.urlOrigin
            )
        )
    }

    fun fromEntityLis(entities: List<CharactersResponse>): List<CharactersModel> {
        return entities.map { mapFromEntity(it) }
    }

}