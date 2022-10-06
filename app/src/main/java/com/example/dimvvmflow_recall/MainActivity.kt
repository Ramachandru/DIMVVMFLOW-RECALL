package com.example.dimvvmflow_recall

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dimvvmflow_recall.model.ResultData
import com.example.dimvvmflow_recall.viewmodel.TodosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var todoAdappter : TodoAdapter
    lateinit var recyclerView : RecyclerView
    lateinit var progtress : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val todosViewModel: TodosViewModel by viewModels()
        recyclerView= findViewById(R.id.recycle)
        progtress = findViewById(R.id.progredss)
        setUpUIData()
        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                todosViewModel.todosMutableState.collect { resultData ->
                    when (resultData) {
                        is ResultData.Success -> {
                            println("ALl DATA : ${resultData.todosList}")
                            println("ALl DATA SIZE : ${resultData.todosList.size}")
                            //todoAdappter.setUpDataWithAdapter(resultData.todosList)
                            todoAdappter.notifyDataSetChanged()
                            progtress.visibility=View.GONE
                            recyclerView.visibility = View.VISIBLE
                        }
                        is ResultData.Error -> {
                            println("ERROR : ${resultData.errorMsg}")
                        }
                        is ResultData.Loading -> {
                            recyclerView.visibility = View.GONE
                            progtress.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
    fun setUpUIData(){
        val linearLayout=LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager =linearLayout
        todoAdappter.setUpDataWithAdapter(emptyList())
        recyclerView?.adapter = todoAdappter
    }
}