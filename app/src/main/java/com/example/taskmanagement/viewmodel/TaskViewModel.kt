package com.example.taskmanagement.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.taskmanagement.database.TaskDatabase
import com.example.taskmanagement.database.TaskEntry
import com.example.taskmanagement.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    private val repository : TaskRepository

    val getAllTask: LiveData<List<TaskEntry>>
    val getAllPriorityTasks: LiveData<List<TaskEntry>>

    init {
        repository = TaskRepository(taskDao)
        getAllTask = repository.getAllTask()
        getAllPriorityTasks = repository.getAllPriorityTasks()
    }

    fun insert(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(taskEntry)
        }
    }

    fun delete(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(taskEntry)
        }
    }

    fun update(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(taskEntry)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntry>>{
        return repository.searchDatabase(searchQuery)
    }
}