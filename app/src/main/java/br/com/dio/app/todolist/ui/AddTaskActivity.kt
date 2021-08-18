package br.com.dio.app.todolist.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.app.todolist.databinding.ActivityAddTaskBinding
import br.com.dio.app.todolist.extensions.format
import br.com.dio.app.todolist.extensions.text
import br.com.dio.app.todolist.model.Task
import br.com.dio.app.todolist.App
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.Date

class AddTaskActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddTaskBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        //if (intent.hasExtra(TASK_ID)) {
        //    val taskId = intent.getIntExtra(TASK_ID, 0)
        //    TaskDataSource.findById(taskId)?.let {
        //        binding.tilTitle.text = it.title
        //        binding.tilDateIni.text = it.dateIni
        //        binding.tilDateFim.text = it.dateFim
        //        binding.tilHourIni.text = it.hourIni
        //        binding.tilHourFim.text = it.hourIni
        //    }
        //}

        insertListeners()
    }


    private fun insertListeners() {

        binding.tilDateIni.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDateIni.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }


        binding.tilDateFim.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDateFim.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }


        binding.tilHourIni.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker.addOnPositiveButtonClickListener {
                val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour

                binding.tilHourIni.text = "$hour:$minute"
            }

            timePicker.show(supportFragmentManager, null)
        }


        binding.tilHourFim.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker.addOnPositiveButtonClickListener {
                val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour

                binding.tilHourFim.text = "$hour:$minute"
            }

            timePicker.show(supportFragmentManager, null)
        }


        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnNewTask.setOnClickListener {

            if (compareTaskDate(
                    "${binding.tilDateIni.text} ${binding.tilHourIni.text}",
                    "${binding.tilDateFim.text} ${binding.tilHourFim.text}",
                ) < 0)  {
                val task = Task(
                    title = binding.tilTitle.text,
                    dateIni = binding.tilDateIni.text,
                    dateFim = binding.tilDateFim.text,
                    hourIni = binding.tilHourIni.text,
                    hourFim = binding.tilHourFim.text
                )
                mainViewModel.insert(task)
                //setResult(Activity.RESULT_OK)
                finish()
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("A data de fim tem que ser maior que a data de início!")
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
    }


    fun compareTaskDate(dataIni: String, dataFim: String): Int {

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val strDateIni = sdf.parse(dataIni)
        val strDateFim = sdf.parse(dataFim)

        if (strDateIni?.after(strDateFim) == true) {
            return 1
        } else if (strDateIni?.before(strDateFim) == true) {
            return -1
        } else {
            return 0
        }
    }

    companion object {
        const val TASK_ID = "task_id"
    }

}