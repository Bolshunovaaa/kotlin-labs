package com.example.Lab9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.Lab9.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var database: RoomDB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            RoomDB::class.java, "room"
        ).build()
        val notesDao = database.notesDao()

        binding.addBtn.setOnClickListener{
            val note = Note(
                name = binding.noteName.text.toString(),
                title = binding.noteTitle.text.toString(),
                body = binding.noteBody.text.toString(),
                creationDate = binding.noteCreationDate.text.toString()
            )
            GlobalScope.launch {
                notesDao.insertAll(note)
            }
            Toast.makeText( applicationContext, "Note added", Toast.LENGTH_LONG).show()
        }

        binding.getAllNotes.setOnClickListener{
            GlobalScope.launch {
                val notes = notesDao.getAll()
                var notesInfo = ""
                notes.forEach{
                    notesInfo += "${it.id}: ${it.name} ${it.title} ${it.creationDate}\n"
                }
                runOnUiThread{
                    binding.textView.text = notesInfo
                }
            }
        }

        binding.deleteButton.setOnClickListener{
            val noteId = binding.idText.text.toString().toIntOrNull()

            if (noteId == null || noteId < 0) {
                Toast.makeText(this, "Invalid index.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                notesDao.deleteById(noteId)
            }
        }
    }
}

