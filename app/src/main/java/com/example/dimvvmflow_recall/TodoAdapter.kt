package com.example.dimvvmflow_recall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dimvvmflow_recall.databinding.TodoAdapterBinding
import com.example.dimvvmflow_recall.model.TodosData

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(todoBinding : TodoAdapterBinding) : RecyclerView.ViewHolder(todoBinding.root){
        var binding  = todoBinding
        fun setData(data : TodosData){
            binding.todoData = data
            binding.executePendingBindings()
        }
    }
    private var listTodo : List<TodosData>? =null;
    fun setUpDataWithAdapter(list : List<TodosData>){
        listTodo = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
       val inflater= LayoutInflater.from(parent.context)
        val todoAdapterBinding = TodoAdapterBinding.inflate(inflater,parent,false)
        return TodoViewHolder(todoAdapterBinding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.setData(listTodo!!.get(position))
    }

    override fun getItemCount(): Int {
       return listTodo!!.size
    }
}