package br.com.dio.app.todolist

import android.app.Application
import br.com.dio.app.todolist.data.AppDatabase
import br.com.dio.app.todolist.data.TaskRepository


class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDAO()) }
}