package com.interview.chatapp.data.mapper

interface DataMapper<F, T> {

    fun map(obj: F): T

    fun mapList(list: List<F>): List<T> {
        val data: MutableList<T> = mutableListOf()
        list.forEach {
            data.add(map(it))
        }

        return data
    }

    fun mapReverse(obj: T): F

    fun mapReverseList(list: List<T>): List<F> {
        val data: MutableList<F> = mutableListOf()
        list.forEach {
            data.add(mapReverse(it))
        }

        return data
    }
}