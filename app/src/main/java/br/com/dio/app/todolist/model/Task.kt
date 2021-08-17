package br.com.dio.app.todolist.model
import java.util.Locale

data class Task(
    val title: String,
    val hourIni: String,
    val hourFim: String,
    val dateIni: String,
    val dateFim: String,
    val id: Int = 0
) {

    private val locale = Locale("pt", "BR")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}