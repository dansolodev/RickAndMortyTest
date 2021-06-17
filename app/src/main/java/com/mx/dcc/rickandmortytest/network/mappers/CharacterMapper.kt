package com.mx.dcc.rickandmortytest.network.mappers

import com.mx.dcc.rickandmortytest.data.detail.CharacterModel
import com.mx.dcc.rickandmortytest.data.detail.CharacterResponse
import com.mx.dcc.rickandmortytest.utils.EntityMapper
import javax.inject.Inject

class CharacterMapper
@Inject
constructor() : EntityMapper<CharacterResponse, CharacterModel> {

    override fun mapFromEntity(entity: CharacterResponse): CharacterModel {
        return CharacterModel(
            id = entity.id,
            name = entity.name,
            episodes = entity.episode
        )
    }

    override fun mapToEntity(domainModel: CharacterModel): CharacterResponse {
        return CharacterResponse(
            id = domainModel.id,
            name = domainModel.name,
            episode = domainModel.episodes
        )
    }

    fun fromEntity(entity: CharacterResponse): CharacterModel {
        return mapFromEntity(entity)
    }

}