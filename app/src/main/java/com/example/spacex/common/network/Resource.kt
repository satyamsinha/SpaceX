package com.example.spacex.common.network

sealed class Resource<T> {
    class Success<T>(val data: T): Resource<T>()
    class Failure<T>(val exception: Exception): Resource<T>()
    class Error<T> : Resource<T>()
}