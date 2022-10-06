package com.example.dimvvmflow_recall.model

sealed class ResultData {
    data class Success(val todosList: MutableMap<Int, List<TodosData>>) : ResultData()

    data class Error(val errorMsg: String) : ResultData()

    object Loading : ResultData()
}
