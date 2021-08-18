package br.com.dio.app.todolist.data

import br.com.dio.app.todolist.model.Task

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TaskRepository (private val dao: TaskDAO) {

        fun insert(task: Task) = runBlocking {
            launch(Dispatchers.IO) {
                dao.insert(task)
            }
        }

        fun deleteByTaskId(cardId: Int) = runBlocking {
            launch(Dispatchers.IO) {
                dao.deleteByCardId(cardId)
            }
        }

        fun getAll() = dao.getAll()
}