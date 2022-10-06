package com.example.dimvvmflow_recall.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dimvvmflow_recall.model.ResultData
import com.example.dimvvmflow_recall.model.TodosData
import com.example.dimvvmflow_recall.network.ApiHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val apiHelper: ApiHelper
) : ViewModel() {
    private var _todosMutableState: MutableStateFlow<ResultData> =
        MutableStateFlow<ResultData>(ResultData.Success(mutableMapOf<Int, List<TodosData>>()))
    var todosMutableState: MutableStateFlow<ResultData> = _todosMutableState

    init {
        getLoadingDataFromServer()
    }

    fun getLoadingDataFromServer() {
        _todosMutableState.value = ResultData.Loading
        viewModelScope.launch {
            val todosMap = mutableMapOf<Int, List<TodosData>>()
            apiHelper.getTodos()
                .map {
                    it.filter {
                        todosMap.orderUserDataListAsPerId(it)
                    }
                    todosMap
                }
                .flowOn(Dispatchers.IO)
                .catch { exception ->
                    _todosMutableState.value = ResultData.Error(exception.message!!)
                }
                .collect {
                    _todosMutableState.value = ResultData.Success(it)
                }
        }
    }

    fun MutableMap<Int, List<TodosData>>.orderUserDataListAsPerId(
        todos: TodosData
    ): Boolean {
        if (!this.containsKey(todos.userId)) {
            val list = mutableListOf<TodosData>()
            list.add(todos)
            this.put(todos.userId, list)
        } else {
            val list = this.get(todos.userId) as MutableList
            list.add(todos)
            this.put(todos.userId, list)
        }
        return true
    }
}