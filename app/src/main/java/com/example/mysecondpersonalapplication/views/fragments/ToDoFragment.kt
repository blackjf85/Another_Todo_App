package com.example.mysecondpersonalapplication.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysecondpersonalapplication.adapters.ToDoAdapter
import com.example.mysecondpersonalapplication.data.ToDo
import com.example.mysecondpersonalapplication.databinding.FragmentTodoBinding
import com.example.mysecondpersonalapplication.viewmodels.TodoViewModel

class ToDoFragment: Fragment() {
    private var _binding: FragmentTodoBinding? = null
    private val binding: FragmentTodoBinding get() = _binding!!

    private lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[TodoViewModel::class.java]

        with(binding){
            viewModel.toDos.observe(viewLifecycleOwner){toDos ->
                itemRv.apply{
                    adapter = ToDoAdapter(toDos)
                    layoutManager =
                        LinearLayoutManager(requireContext())
                }
            }

            doneTv.text = ""
            countTv.text = "Task Count: ${viewModel.idNum}"

            addBtn.setOnClickListener {

                if(itemEt.text.toString() != ""){
                    viewModel.setTodoItem(itemEt.text.toString())
                    val toDoItem = viewModel.todo.value.toString()
                    val id = viewModel.idNum
                    val toDo = ToDo(toDoItem, id)
                    viewModel.addTodo(toDo)
                    itemEt.text?.clear()
                    doneTv.text = "Finished?"
                    countTv.text = "Task Count: ${viewModel.idNum}"

                    if(itemIL.hint.toString() == "Task cannot be empty"){
                        itemIL.hint = "Add ToDo Item"
                    }
                }else{
                    itemIL.hint = "Task cannot be empty"
                }
            }

            clearBtn.setOnClickListener {
                viewModel.removeToDos()
                doneTv.text = ""
                countTv.text = "Task Count: ${viewModel.idNum}"
            }

            backBtn.setOnClickListener {
                val directions =
                    ToDoFragmentDirections.actionToDoFragmentToWelcomeFragment()
                findNavController().navigate(directions)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}