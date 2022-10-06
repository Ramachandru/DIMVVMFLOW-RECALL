package com.example.dimvvmflow_recall.network

import com.example.dimvvmflow_recall.model.TodosData
import retrofit2.http.GET

interface ApiData {
    companion object{
        const val BASEURL : String="https://jsonplaceholder.typicode.com/"
    }

    @GET("todos")
    suspend fun getTodos() : List<TodosData>
}