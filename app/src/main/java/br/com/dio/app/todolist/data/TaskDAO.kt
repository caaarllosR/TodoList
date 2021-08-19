package br.com.dio.app.todolist.data
import br.com.dio.app.todolist.model.Task

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface TaskDAO {

    @Query("SELECT * FROM Task")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE id = :taskId")
    fun getByTaskId(taskId: Int): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM Task WHERE id = :taskId")
    fun deleteByTaskId(taskId: Int);
}