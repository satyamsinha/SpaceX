package com.example.spacex.common.data.repository

import com.example.spacex.common.network.Resource
import com.example.spacex.common.network.exceptions.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {

    fun <T, R> toResourceFlow(response: Response<T>, transform: (T) -> R): Flow<Resource<R>> {
        return flow<Resource<R>> {
            if (response.isSuccessful) {
                response.body()?.let { emit(Resource.Success(data =  transform(it))) }
            } else {
                response.errorBody()?.let {
                    emit(Resource.Failure(ApiException(message = response.errorBody().toString())))
                } ?: emit(Resource.Error())
            }
        }.flowOn(Dispatchers.IO)
    }

}