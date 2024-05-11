package com.example.taskmanagement.repository

import androidx.lifecycle.LiveData
import com.example.taskmanagement.database.TaskDao
import com.example.taskmanagement.database.TaskEntry

class TaskRepository (val taskDao: TaskDao){

    suspend fun insert(taskEntry: TaskEntry) = taskDao.insert(taskEntry)

    suspend fun updateData(taskEntry: TaskEntry) = taskDao.update(taskEntry)

    suspend fun deleteItem(taskEntry: TaskEntry) = taskDao.delete(taskEntry)

    suspend fun deleteAll(){
        taskDao.deleteAll()
    }

    fun getAllTask() : LiveData<List<TaskEntry>> = taskDao.getAllTasks()
}