package com.example.dimvvmflow_recall.network

import com.example.dimvvmflow_recall.model.TodosData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiHelperImpt @Inject constructor(private val apiData: ApiData) : ApiHelper {
    override fun getTodos(): Flow<List<TodosData>> {
        return flow {
            emit(apiData.getTodos())
        }
    }
}