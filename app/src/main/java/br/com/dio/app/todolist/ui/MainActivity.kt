package br.com.dio.app.todolist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.app.todolist.databinding.ActivityMainBinding
import br.com.dio.app.todolist.App

class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val adapter by lazy { TaskListAdapter() }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvTasks.adapter = adapter
        getAllTask()
        insertListeners()
    }

    private fun insertListeners() {
        binding.fab.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerDelete = {
            mainViewModel.deleteByTaskId(it.id)
            getAllTask()
        }
    }

   private fun getAllTask() {
            mainViewModel.getAll().observe(this, { task ->
            binding.includeEmpty.emptyState.visibility = if (task.isEmpty()) View.VISIBLE
            else View.GONE

            adapter.submitList(task)
        })
   }
    companion object {
        private const val CREATE_NEW_TASK = 1000
    }
}