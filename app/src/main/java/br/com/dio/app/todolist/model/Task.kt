package br.com.dio.app.todolist.model
import java.util.Locale
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val hourIni: String,
    val hourFim: String,
    val dateIni: String,
    val dateFim: String
)
