package com.example.lab10

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lab10.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getTodos()

        binding.button.setOnClickListener {
            val retrofit = RetrofitClient.getInstance()
            val apiInterface = retrofit.create(ApiInterface::class.java)
            lifecycleScope.launch {
                try {
                    val todo = Todo(
                        id = binding.todoId.text.toString().toInt(),
                        userId = binding.userId.text.toString().toInt(),
                        title = binding.todoTitle.text.toString(),
                        completed = binding.todoCompleted.isActivated,
                    )
                    val response = apiInterface.addTodo(todo)
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(applicationContext, "new todo added", Toast.LENGTH_SHORT).show()
                    }
                } catch (Ex: Exception) { }
            }
        }
    }

    private fun getTodos(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launch {
            val response = apiInterface.getTodoById(1)
            var todo = response.body()
            binding.textView.text = "userId: ${todo?.userId}\nid: ${todo?.id}\ntitle: ${todo?.title}\ncompleted: ${todo?.completed}"
        }
    }
}