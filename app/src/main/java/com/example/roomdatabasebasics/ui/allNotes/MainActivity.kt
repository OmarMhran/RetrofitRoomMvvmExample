package com.example.roomdatabasebasics.ui.allNotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasebasics.*
import com.example.roomdatabasebasics.data.NoteDao
import com.example.roomdatabasebasics.model.Note
import com.example.roomdatabasebasics.ui.DeleteNoteInterface
import com.example.roomdatabasebasics.ui.NoteClickInterface
import com.example.roomdatabasebasics.ui.NoteViewModel
import com.example.roomdatabasebasics.ui.addNote.AddNoteActivity
import com.example.roomdatabasebasics.ui.allNotes.adapter.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NoteClickInterface, DeleteNoteInterface {

    lateinit var noteAdapter: NoteAdapter
    lateinit var viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
        setUpRecyclerView()



        viewModel.notes.observe(this, Observer { list ->
            noteAdapter.differ.submitList(list!!)
        })



        fbAddNote.setOnClickListener {
            toAddActivity()
        }


    }

    private fun toAddActivity() {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    private fun setUpRecyclerView() {
        noteAdapter = NoteAdapter(this, this)
        rvNotes.apply {
            adapter = noteAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDeleteNote(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClicked(note: Note) {
        val intent = Intent(this, AddNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDesc", note.description)
        intent.putExtra("noteId", note.id)

        startActivity(intent)
        this.finish()
    }
}