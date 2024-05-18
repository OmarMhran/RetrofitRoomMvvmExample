package com.example.roomdatabasebasics.data

import androidx.lifecycle.LiveData
import com.example.roomdatabasebasics.model.Note

class NoteRepository (private val noteDao: NoteDao) {

    val allNotesLiveData :LiveData<List<Note>> = noteDao.getNotes()

    suspend fun insert(note: Note){
        noteDao.addNote(note)
    }

    suspend fun delete(note: Note){
        noteDao.deleteNote(note)
    }

    suspend fun update(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun getNoteById(id:Int): Note?{
        return noteDao.getNoteById(id)
    }

}