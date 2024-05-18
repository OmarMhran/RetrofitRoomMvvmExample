package com.example.roomdatabasebasics.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasebasics.data.NoteRepository
import com.example.roomdatabasebasics.data.RoomDataBaseClass
import com.example.roomdatabasebasics.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(context: Application) : AndroidViewModel(context) {

    val notes: LiveData<List<Note>>
    val repo: NoteRepository
    init {
        val dao = RoomDataBaseClass.getDatabase(context).getDao()
        repo = NoteRepository(dao)
        notes = repo.allNotesLiveData
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repo.insert(note)
    }

    fun deleteNote(note:Note) = viewModelScope.launch (Dispatchers.IO){
        repo.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repo.update(note)
    }



    fun getNotById(id: Int) = viewModelScope.launch(Dispatchers.IO){
        repo.getNoteById(id)
    }

}