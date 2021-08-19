package br.com.dio.app.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dio.app.todolist.data.TaskRepository
import br.com.dio.app.todolist.model.Task
import kotlinx.coroutines.Job

class MainViewModel(private val taskRepository: TaskRepository): ViewModel() {

    fun insert(task: Task) {
        taskRepository.insert(task)
    }

    fun deleteByTaskId(taskId: Int) {
        taskRepository.deleteByTaskId(taskId)
    }

    fun getAll(): LiveData<List<Task>> {
        return taskRepository.getAll()
    }

}

// como nao esta sendo utilizada injecao de dependencia vamos ter que injetar o viewmodel em um provider de forma manual
class MainViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}