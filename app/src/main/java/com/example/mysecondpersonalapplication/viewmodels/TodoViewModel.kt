package com.example.mysecondpersonalapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysecondpersonalapplication.data.ToDo

class TodoViewModel: ViewModel() {

    var idNum = 0

    private var _toDo: MutableLiveData<String> = MutableLiveData()
    val todo: LiveData<String> get() = _toDo

    private var _toDos: MutableLiveData<List<ToDo>> = MutableLiveData()
    val toDos: LiveData<List<ToDo>> get() = _toDos
    val toDoList: MutableList<ToDo> = mutableListOf()

    fun addTodo(toDo: ToDo) {
        toDoList.add(toDo)
        _toDos.value = toDoList
        incrementId()
    }

    fun setTodoItem(todo: String){
        _toDo.value = todo
    }

    fun removeToDos(){
        toDoList.clear()
        idNum = 0
        _toDos.value = toDoList
    }

    private fun incrementId(){
        idNum++
    }

}