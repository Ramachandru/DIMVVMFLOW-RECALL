package com.example.dimvvmflow_recall.network

import com.example.dimvvmflow_recall.model.TodosData
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    fun getTodos(): Flow<List<TodosData>>
}