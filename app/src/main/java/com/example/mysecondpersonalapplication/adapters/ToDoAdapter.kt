package com.example.mysecondpersonalapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysecondpersonalapplication.data.ToDo
import com.example.mysecondpersonalapplication.databinding.ItemTodoBinding

class ToDoAdapter(
    private val toDoList: List<ToDo>
): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(toDoList[position])
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    class ToDoViewHolder(private val binding: ItemTodoBinding):
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(toDo: ToDo) {

            with(binding) {
                val originalText = "${toDo.id + 1} ${toDo.todoItem}"
                val originalBtnText = removeBtn.text.toString()
                toDoTv.text = "${toDo.id + 1} ${toDo.todoItem}"
                removeBtn.setOnClickListener{

                    if(toDoTv.text.toString() == "${toDo.id + 1} Task Complete"){
                        toDoTv.text = originalText
                        removeBtn.text = originalBtnText
                    }else{
                        toDoTv.text = "${toDo.id + 1} Task Complete"
                        removeBtn.text = "Undo"
                    }
                }
            }
        }
    }
}