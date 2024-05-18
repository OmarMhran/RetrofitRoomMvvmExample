package com.example.roomdatabasebasics.ui.addNote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabasebasics.ui.NoteViewModel
import com.example.roomdatabasebasics.R
import com.example.roomdatabasebasics.model.Note
import com.example.roomdatabasebasics.ui.allNotes.MainActivity
import kotlinx.android.synthetic.main.activity_add_note.*
import java.time.LocalDateTime

class AddNoteActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel

    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        val type = intent.getStringExtra("noteType")
        if (type.equals("Edit")){
            val title = intent.getStringExtra("noteTitle")
            val desc = intent.getStringExtra("noteDesc")
            noteId = intent.getIntExtra("noteId",-1)
            btnSaveNote.text = "Update Note"
            etTitle.setText(title)
            etDesc.setText(desc)

        }else{
            btnSaveNote.text = "Save Note"
        }


        btnSaveNote.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()

            if (type.equals("Edit")){
                if(title.isNotEmpty() && desc.isNotEmpty()){
                    val current = LocalDateTime.now()
                    val editedNote = Note(title,desc,current.toString())
                    editedNote.id = noteId
                    viewModel.updateNote(editedNote)
                    Toast.makeText(this,"Note Updated Successfully",Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this,"Fields mustn't be empty",Toast.LENGTH_SHORT).show()
                }
            }else{
                if(title.isNotEmpty() && desc.isNotEmpty()){
                    val current = LocalDateTime.now()
                    val note = Note(title,desc,current.toString())
                    viewModel.addNote(note)
                    Toast.makeText(this,"Note Added Successfully",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Fields mustn't be empty",Toast.LENGTH_SHORT).show()
                }
            }

            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }

    }
}