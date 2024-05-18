package com.example.roomdatabasebasics.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabasebasics.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM NoteTable WHERE id =:searchedID")
    suspend fun getNoteById(searchedID: Int): Note?

    @Query("SELECT * FROM NoteTable")
    fun getNotes(): LiveData<List<Note>>

}